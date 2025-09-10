package jpabook.jpashop.service;

import jpabook.jpashop.domain.Item;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //상품 등록
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    //전체 아이템 조회
    public List<Item> findAll() {
        return itemRepository.findAll();
    }


    public Item findById(Long id) {
        return itemRepository.findOne(id);
    }


}
