package no.kristiania.paymentservice.entity

import javax.persistence.*

@Entity
class Payment (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_generator")
    @SequenceGenerator(name = "payment_id_generator", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "item_name")
    val itemName: String,

    @Column(name = "amount")
    val amount: Double,
        )