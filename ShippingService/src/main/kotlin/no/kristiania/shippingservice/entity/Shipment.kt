package no.kristiania.shippingservice.entity

import javax.persistence.*

@Entity
class Shipment (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipment_id_generator")
    @SequenceGenerator(name = "shipment_id_generator", allocationSize = 1)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "item_name")
    val item: String,

    @Column(name = "destination")
    //TODO: Add destination??
    val destination: String = "Norway"
        )