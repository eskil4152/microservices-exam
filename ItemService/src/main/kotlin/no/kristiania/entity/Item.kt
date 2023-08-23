package no.kristiania.entity

import javax.persistence.*

@Entity
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_sequence")
    @SequenceGenerator(
        name = "item_id_sequence",
        allocationSize = 1
    )
    @Column(name = "id")
    val id: Long,

    @Column(name = "name")
    val name: String,

    @Column(name = "price")
    val price: Double,

    @Column(name = "quantity")
    var quantity: Int
)