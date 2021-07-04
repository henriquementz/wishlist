package br.com.wishlist.restapi.model.api;

import br.com.wishlist.restapi.error.response.ApiErrorResponse;
import br.com.wishlist.restapi.model.request.ItemRequest;
import br.com.wishlist.restapi.model.response.ItemResponse;
import br.com.wishlist.restapi.model.response.PageItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Tag(description = "All wishlist operations", name = "WishlistController")
public interface WishlistControllerApi {

    @Operation(summary = "Add an item in wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item added in wishlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Wishlist maximum size reached | Item already added",
                    content = @Content)})
    ResponseEntity<ItemResponse> add(@Valid @RequestBody ItemRequest itemRequest, @PathVariable Long clientId);


    @Operation(summary = "Find all items in wishlist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wishlist was found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PageItemResponse.class))})})
    ResponseEntity<PageItemResponse> getAll(@PathVariable Long clientId,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Find an item in wishlist by itemId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found in wishlist",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ItemResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Item not found",
                    content = @Content)})
    ResponseEntity<ItemResponse> get(@PathVariable Long clientId, @PathVariable Long itemId);

    @Operation(summary = "Delete an item in wishlist by itemId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item deleted in wishlist"),
            @ApiResponse(responseCode = "404", description = "Item not found",
                    content = @Content)})
    ResponseEntity<Void> delete(@PathVariable Long clientId, @PathVariable Long itemId);

}
