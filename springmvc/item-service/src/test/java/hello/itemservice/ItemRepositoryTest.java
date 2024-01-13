package hello.itemservice;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {

    ItemRepository repository;

    @BeforeEach
    public void setUp(){
        repository = new ItemRepository();
    }

    @AfterEach
    public void clear(){
        repository.clear();
    }

    @Test
    public void save(){
        // given
        Item item = new Item("itemA", 1000, 5);

        // when
        Item savedItem = repository.save(item);

        // then
        assertThat("itemA").isEqualTo(savedItem.getName());
    }

    @Test
    public void 상품목록(){
        //given
        Item itemA = new Item();
        itemA.setName("itemA");
        itemA.setPrice(1000);
        itemA.setQuentity(5);

        Item itemB = new Item();
        itemB.setName("itemB");
        itemB.setPrice(5000);
        itemB.setQuentity(10);

        repository.save(itemA);
        repository.save(itemB);

        //when
        List<Item> items = repository.allList();

        //then
        assertThat(2).isEqualTo(items.size());
        assertThat(items).contains(itemA, itemB);
    }

    @Test
    public void 상품상세(){
        //given
        Item itemA = new Item("itemA", 1000, 5);
        repository.save(itemA);

        //when
        Item detailItem = repository.findById(itemA.getId());

        //then
        assertThat("itemA").isEqualTo(detailItem.getName());
    }

    @Test
    public void 상품수정(){
        // given
        Item itemA = new Item("itemA", 1000, 5);
        repository.save(itemA);

        // when
        itemA.setName("itemB");
        Item editedItem = repository.update(itemA.getId(), itemA);

        // then
        assertThat("itemB").isEqualTo(editedItem.getName());
    }


}
