package no.kristiania.repository

import no.kristiania.entity.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: PagingAndSortingRepository<Item, Long>{

    override fun findAll(pageable: Pageable): Page<Item>
}