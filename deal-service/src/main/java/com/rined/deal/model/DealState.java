package com.rined.deal.model;

public enum DealState {

    // первая стадия, когда consumer согласился на предложение
    WAIT_PROVIDER,

    // поставщик отказался
    PROVIDER_DECLINE,

    // потребитель отказался
    CONSUMER_DECLINE,

    // провайдер согласился на сделку
    PROVIDER_ACCEPT,

    // согласны обе стороны
    ACCEPTED,

    // ожидается заполнение информации от потребителя и поставщика
    WAIT_INFO,

    // согласовано - здесь становится доступна инфа о поставщике и потребителе
    AGREED,

    // в процессе выполнения
    IN_PROGRESS,

    // сделка выполнена
    DONE
}
