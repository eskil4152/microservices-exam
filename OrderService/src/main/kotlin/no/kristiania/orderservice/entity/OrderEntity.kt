package no.kristiania.orderservice.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_number_generator")
    @SequenceGenerator(name = "order_number_generator", allocationSize = 1)
    @Column(name = "order_number")
    val orderNumber: Long? = null,

    @Column(name = "ordered_item_name")
    val orderedItemName: String,

    @Column(name = "sum")
    val orderSum: Double,

    @Column(name = "payed")
    var payed: Boolean = false,

    @Column(name = "shipped")
    var shipped: Boolean = false,

    @Column(name = "time_of_order")
    val timeOfOrder: LocalDateTime
    )