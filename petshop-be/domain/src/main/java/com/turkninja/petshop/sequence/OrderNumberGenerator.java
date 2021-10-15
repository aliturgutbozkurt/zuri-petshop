package com.turkninja.petshop.sequence;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class OrderNumberGenerator implements IdentifierGenerator {
    public static final String NAME = "order-number-generator";
    public static final String STRATEGY = "com.turkninja.petshop.sequence.OrderNumberGenerator";
    private static final String PREFIX = "O";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = String.format("select max(%s) from %s",
                session.getEntityPersister(obj.getClass().getName(), obj).getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        String result = (String) session.createQuery(query).getSingleResult();

        long maxOrderNumber = 0L;
        if(result != null){
            maxOrderNumber = Long.parseLong(result.substring(PREFIX.length()).replace(PREFIX, ""));
        }

        return PREFIX + String.format("%08d", (maxOrderNumber + 1));
    }
}