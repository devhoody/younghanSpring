package hello.itemservice.web.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.allList();
        model.addAttribute("list", items);
        return "basic/items";
    }

    /**
     * 테스트용 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 5));
        itemRepository.save(new Item("itemB", 20000, 10));
    }

    @GetMapping("add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("add")
//    public String addItemV1(
//            @RequestParam("itemName") String itemName,
//            @RequestParam("price") Integer price,
//            @RequestParam("quantity") Integer quantity
//    ){
//        Item item = new Item(itemName, price, quantity);
//        Item save = itemRepository.save(item);
//        log.info("itemName = {}", save.getItemName());
//        return "redirect:/basic/items";
//    }

//    @PostMapping("add")
//    public String addItemV2(
//            @ModelAttribute Item item
//    ){
////        Item item = new Item(itemName, price, quantity);
//        Item save = itemRepository.save(item);
//        log.info("itemName = {}", save.getItemName());
//        return "redirect:/basic/items";
//    }

//    @PostMapping("add")
//    public String addItemV5(Item item){
//        Item save = itemRepository.save(item);
//        return "redirect:/basic/items/" + item.getId();
//    }

    @PostMapping("add")
    public String addItemV5(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true); // 쿼리스트링으로 들어감

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable(name = "itemId") Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String getEdit(@PathVariable(name = "itemId") Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        log.info("itemName = {}",item.getItemName());
        return "basic/editForm";
    }

//    @PostMapping("/{itemId}/edit")
//    public String editItemV1(@PathVariable(name = "itemId") Long id,
//                            @RequestParam("itemName") String itemName,
//                           @RequestParam("price") Integer price,
//                           @RequestParam("quantity") Integer quantity
//                           ){
//        Item findItem = itemRepository.findById(id);
//        findItem.setItemName(itemName);
//        findItem.setPrice(price);
//        findItem.setQuantity(quantity);
//        itemRepository.update(id, findItem);
//        log.info("itemName ={}", itemName);
//
//
//        return "redirect:/basic/items/{itemId}";
//    }

    @PostMapping("/{itemId}/edit")
    public String editItemV2(@PathVariable(name = "itemId") Long id,
                             @ModelAttribute Item item
    ){
        itemRepository.update(id, item);
        log.info("itemName ={}", item.getItemName());

        return "redirect:/basic/items/{itemId}";
    }


}
