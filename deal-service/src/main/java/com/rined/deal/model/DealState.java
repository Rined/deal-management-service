package com.rined.deal.model;

public enum DealState {
    // поставщик отказался
    PROVIDER_DECLINE(null),

    // потребитель отказался
    CONSUMER_DECLINE(null),

    // сделка выполнена
    DONE(null),

    // в процессе выполнения
    IN_PROGRESS(DONE),

    // согласовано - здесь становится доступна инфа о поставщике и потребителе
    AGREED(IN_PROGRESS),

    // ожидается заполнение информации от потребителя и поставщика
    WAIT_INFO(AGREED),

    // согласны обе стороны
    ACCEPTED(WAIT_INFO),

    // провайдер согласился на сделку
    PROVIDER_ACCEPT(ACCEPTED),

    // первая стадия, когда consumer согласился на предложение
    WAIT_PROVIDER(PROVIDER_ACCEPT);

    private final DealState nextState;

    DealState(DealState nextState) {
        this.nextState = nextState;
    }

    public DealState getNextState() {
        return nextState;
    }
}
