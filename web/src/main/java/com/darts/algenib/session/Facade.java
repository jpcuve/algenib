package com.darts.algenib.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.io.Serializable;

/**
 * Created by jpc on 2/19/16.
 */
@Stateless(name = "algenib/facade")
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED) // default, starts a new transaction if none present
public class Facade extends Crud implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Facade.class);
}
