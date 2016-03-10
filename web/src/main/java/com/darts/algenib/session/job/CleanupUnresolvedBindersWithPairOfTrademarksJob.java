package com.darts.algenib.session.job;

import com.darts.algenib.bean.JobItem;
import com.darts.algenib.entity.Binder;
import com.darts.algenib.entity.Right;
import com.darts.algenib.entity.TrademarkRight;
import com.darts.algenib.session.Crud;
import com.darts.algenib.session.Facade;
import com.darts.algenib.util.BatchIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Future;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by jpc on 2/19/16.
 */
@Singleton(name = "algenib/cleanup-unresolved-binders-with-pair-of-trademarks-job")
@LocalBean
public class CleanupUnresolvedBindersWithPairOfTrademarksJob implements Job<Void> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CleanupUnresolvedBindersWithPairOfTrademarksJob.class);
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    @Inject
    private Facade facade;

    private static String deleteTrademarkRight(final TrademarkRight trademarkRight){
        final StringBuilder sb = new StringBuilder();
        return sb.toString();


    }

    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.NEVER)
    @Override
    public Future<Void> process(@NotNull JobItem jobItem) {
        final List<Long> binderIds = facade.findByNamedQuery(Long.class, Binder.BINDER_IDS_FOR_TWO_DEFENDANT_TRADEMARKS);
//        final List<Long> binderIds = Collections.singletonList(1825886L);
        jobItem.addMessage("Binders found: %s", binderIds.size());
        final BiPredicate<String, String> stringEquals = (s1, s2) -> (s1 == null ? "" : s1).equals(s2 == null ? "" : s2);
        final BiPredicate<Right, Right> rightEquals = (r1, r2) ->
                "TM".equals(r1.getDisc()) && "TM".equals(r2.getDisc()) &&
                !r1.isOpponent() && !r2.isOpponent() && stringEquals.test(((TrademarkRight) r1).getTrademarkType(), ((TrademarkRight) r2).getTrademarkType()) &&
                stringEquals.test(((TrademarkRight) r1).getName(), ((TrademarkRight) r2).getName()) &&
                stringEquals.test(((TrademarkRight) r1).getTypeDescription(), ((TrademarkRight) r2).getTypeDescription()) &&
                r1.getNiceClasses().equals(r2.getNiceClasses()) &&
                r1.getLocarnoClasses().equals(r2.getLocarnoClasses()) &&
                r1.getViennaClasses().equals(r2.getViennaClasses()) &&
                r1.getImages().equals(r2.getImages());
        int i = 0;
        final List<Long> trademarkToBeRemovedIds = new ArrayList<>();
        for (long binderId: binderIds){
            if (i % 1000 == 0){
                jobItem.progress(i, binderIds.size());
            }
            final List<Right> rights = facade.findByNamedQuery(Right.class, Right.RIGHT_FIND_BY_BINDER_FETCH_ALL, Crud.Parameters.build("binderId", binderId));
            if (rightEquals.test(rights.get(0), rights.get(1))){
                if (rights.get(0).getHonors().size() == 0){
                    trademarkToBeRemovedIds.add(rights.get(0).getId());
                } else if (rights.get(1).getHonors().size() == 0){
                    trademarkToBeRemovedIds.add(rights.get(1).getId());
                }
            }
            i++;
        }
        jobItem.addMessage("Trademark to be removed count: %s", trademarkToBeRemovedIds.size());
        jobItem.addMessage("Done.");
        jobItem.done();
        final String filename = "cleanup_unresolved_binders_with_pair_of_trademarks";
        try(
                final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(String.format("%s_%s.zip", filename, DATE_FORMAT.format(new Date()))));
                final PrintWriter w = new PrintWriter(zos)
        ) {
            zos.putNextEntry(new ZipEntry(String.format("%s.sql", filename)));
            for (Iterator<List<Long>> it = new BatchIterator<>(trademarkToBeRemovedIds, 1000); it.hasNext(); ) {
                final List<Long> ids = it.next();
                final String separated = ids.stream().map(id -> Long.toString(id)).collect(Collectors.joining(","));
                w.printf("delete from right_image where right_fk in (%s);%n", separated);
                w.printf("delete from right_locarno where right_fk in (%s);%n", separated);
                w.printf("delete from right_nice where right_fk in (%s);%n", separated);
                w.printf("delete from right_vienna where right_fk in (%s);%n", separated);
                w.printf("delete from ip_right where id in (%s);%n", separated);
                w.printf("delete from trademark_right_meaning where trademark_right_fk in (%s);%n", separated);
                w.printf("delete from trademark_right_script where trademark_right_fk in (%s);%n", separated);
                w.printf("delete from trademark_right where id in (%s);%n", separated);
                w.flush();
            }
            zos.closeEntry();
        } catch(IOException e){
            LOGGER.error("cannot write output file");
        }
        return null;
    }
}
