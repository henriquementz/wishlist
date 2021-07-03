package br.com.wishlist.restapi;

import br.com.wishlist.domain.Item;
import br.com.wishlist.restapi.mapper.GroupMapper;
import br.com.wishlist.restapi.model.request.ItemRequest;
import br.com.wishlist.restapi.model.response.ItemResponse;
import br.com.wishlist.restapi.model.response.PageItemResponse;
import br.com.wishlist.usecase.AddItemUseCase;
import br.com.wishlist.usecase.DeleteItemUseCase;
import br.com.wishlist.usecase.FindItemsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist/")
public class WishlistController {

    @Autowired
    private AddItemUseCase addItemUseCase;

    @Autowired
    private FindItemsUseCase findItemsUseCase;

    @Autowired
    private DeleteItemUseCase deleteItemUseCase;

    @PostMapping(value = "/{clientId}/add")
    public ResponseEntity<ItemResponse> post(@RequestBody ItemRequest itemRequest, @PathVariable Long clientId) {
        Item response = addItemUseCase.add(GroupMapper.mapFromRequest(itemRequest, clientId));
        return new ResponseEntity<>(GroupMapper.mapFromDomain(response), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<PageItemResponse> get(@PathVariable Long clientId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        List<Item> response = findItemsUseCase.findAll(clientId, PageRequest.of(page, size));
        Long quantity = findItemsUseCase.countAll(clientId);
        return ResponseEntity.ok(GroupMapper.mapFromDomain(response, quantity));
    }

    @GetMapping(value = "/{clientId}/product/{productId}")
    public ResponseEntity<ItemResponse> get(@PathVariable Long clientId, @PathVariable Long productId) {
        Item response = findItemsUseCase.findByProductId(clientId, productId);
        return ResponseEntity.ok(GroupMapper.mapFromDomain(response));
    }

    @DeleteMapping(value = "/{clientId}/product/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId, @PathVariable Long productId) {
        deleteItemUseCase.deleteProduct(clientId, productId);
        return ResponseEntity.ok().build();
    }

}
