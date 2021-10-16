package io.npee.springwebbasic.web.basic;

import io.npee.springwebbasic.domain.DeliveryCode;
import io.npee.springwebbasic.domain.Item;
import io.npee.springwebbasic.domain.ItemRepository;
import io.npee.springwebbasic.domain.ItemType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJI", "제주");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
        return deliveryCodes;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "basic/addForm";
    }

    // @PostMapping("/add")
    public String addItem(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);
        return "/basic/item";
    }

    // @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        itemRepository.save(item);
        // model.addAttribute("item", item); // @ModelAttribute 에 의해 Model 자동 추가
        return "/basic/item";
    }

    // @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {

        itemRepository.save(item);
        // 속성 생략 시 class 의 첫 글자를 소문자로 치환해서 Model에 추가
        return "/basic/item";
    }

    // @PostMapping("/add")
    public String addItemV4(Item item) {

        itemRepository.save(item);
        // 생략 시 @ModelAttribute 자동 적용
        return "/basic/item";
    }

    // @PostMapping("/add")
    public String addItemV5(Item item) {

        itemRepository.save(item);
        // 생략 시 @ModelAttribute 자동 적용
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {

        log.info("item.getOpen() {}", item.getOpen());
        log.info("item.getRegions() {}", item.getRegions());
        log.info("item.getItemType() {}", item.getItemType());
        log.info("item.getDeliveryCode() {}", item.getDeliveryCode());
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        // 생략 시 @ModelAttribute 자동 적용
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

}
