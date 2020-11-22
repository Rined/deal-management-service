package com.rined.payment.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "transactions")
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
public class Transact {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String transactionId;

    @Column(name = "from_user")
    private String fromUserId;

    @Column(name = "to_user")
    private String toUserId;

    @Column(name = "ammount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    public Transact(String fromUserId, String toUserId, BigDecimal amount, PaymentType type) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.type = type;
    }
}
