package br.com.wishlist.restapi;

import br.com.wishlist.domain.Item;
import br.com.wishlist.restapi.mapper.GroupMapper;
import br.com.wishlist.restapi.model.api.WishlistControllerApi;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist/client")
public class WishlistController implements WishlistControllerApi {

    @Autowired
    private AddItemUseCase addItemUseCase;

    @Autowired
    private FindItemsUseCase findItemsUseCase;

    @Autowired
    private DeleteItemUseCase deleteItemUseCase;

    @PostMapping(value = "/{clientId}/add")
    public ResponseEntity<ItemResponse> add(@Valid @RequestBody ItemRequest itemRequest, @PathVariable Long clientId) {
        Item response = addItemUseCase.add(GroupMapper.mapFromRequest(itemRequest, clientId));
        return new ResponseEntity<>(GroupMapper.mapFromDomain(response), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<PageItemResponse> getAll(@PathVariable Long clientId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        List<Item> response = findItemsUseCase.findAll(clientId, PageRequest.of(page, size));
        Long quantity = findItemsUseCase.count(clientId);
        return ResponseEntity.ok(GroupMapper.mapFromDomain(response, quantity));
    }

    @GetMapping(value = "/{clientId}/item/{itemId}")
    public ResponseEntity<ItemResponse> get(@PathVariable Long clientId, @PathVariable Long itemId) {
        Item response = findItemsUseCase.find(clientId, itemId);
        return ResponseEntity.ok(GroupMapper.mapFromDomain(response));
    }

    @DeleteMapping(value = "/{clientId}/item/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable Long clientId, @PathVariable Long itemId) {
        deleteItemUseCase.delete(clientId, itemId);
        return ResponseEntity.ok().build();
    }

}
