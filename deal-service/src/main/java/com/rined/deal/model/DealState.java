package com.rined.deal.model;

public enum DealState {
    // поставщик отказался
    PROVIDER_DECLINE(null),

    // потребитель отказался
    CONSUMER_DECLINE(null),

    // сделка выполнена
    DONE(null),

    // в процессе выполнения
    IN_WORK(DONE),

    // согласовано - здесь становится доступна инфа о поставщике и потребителе
    CONSUMER_PROVIDE_INFO(IN_WORK),

    // ожидается заполнение информации от потребителя и поставщика
    PROVIDER_REQUEST_INFO(CONSUMER_PROVIDE_INFO),

    // согласны обе стороны
    CONSUMER_ACCEPT(PROVIDER_REQUEST_INFO),

    // провайдер согласился на сделку
    PROVIDER_ACCEPT(CONSUMER_ACCEPT);


    private final DealState nextState;

    DealState(DealState nextState) {
        this.nextState = nextState;
    }

    public DealState getNextState() {
        return nextState;
    }
}
