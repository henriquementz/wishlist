package br.com.wishlist.restapi;

import br.com.wishlist.ApiApplication;
import br.com.wishlist.error.exception.ApiException;
import br.com.wishlist.error.exception.WishListErrorCode;
import br.com.wishlist.usecase.AddItemUseCase;
import br.com.wishlist.usecase.DeleteItemUseCase;
import br.com.wishlist.usecase.FindItemsUseCase;
import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:application.yml")
@WebMvcTest(WishlistController.class)
@Import(value = {ApiApplication.class, MockUtil.class})
@ExtendWith(SpringExtension.class)
class WishlistControllerTest {

    @MockBean
    private AddItemUseCase addItemUseCase;

    @MockBean
    private FindItemsUseCase findItemsUseCase;

    @MockBean
    private DeleteItemUseCase deleteItemUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockUtil mockUtil;

    @Test
    public void given_ValidItemRequest_When_add_Then_ExpectedItemSaved() throws Exception {

        when(addItemUseCase.add(any())).thenReturn(mockUtil.getValidItem());

        this.mockMvc.perform(
                post("/api/wishlist/client/1/add")
                        .content(mockUtil.getJsonItemRequest())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void given_ValidItemRequestWithDatabaseOut_When_add_Then_ExpectedInternalServerError() throws Exception {

        when(addItemUseCase.add(any())).thenThrow(new RuntimeException("Database Error"));

        this.mockMvc.perform(
                post("/api/wishlist/client/1/add")
                        .content(mockUtil.getJsonItemRequest())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void given_ValidItemRequestWithWishlistFully_When_add_Then_ExpectedBadRequest() throws Exception {

        when(addItemUseCase.add(any())).thenThrow(new ApiException(WishListErrorCode.WISHLIST_LENGTH_ERROR));

        this.mockMvc.perform(
                post("/api/wishlist/client/1/add")
                        .content(mockUtil.getJsonItemRequest())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void given_ClientWithWishList_When_get_Then_ExpectedPageOfItems() throws Exception {

        when(findItemsUseCase.findAll(anyLong(), any())).thenReturn(mockUtil.getListOfValidItems());

        this.mockMvc.perform(
                get("/api/wishlist/client/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10")
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void given_ClientWithWishList_When_findByItemId_Then_ExpectedItemResponse() throws Exception {

        when(findItemsUseCase.find(anyLong(), anyLong())).thenReturn(mockUtil.getValidItem());

        this.mockMvc.perform(
                get("/api/wishlist/client/1/item/342324")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void given_ClientWithWishList_When_delete_Then_ExpectedItemDeleted() throws Exception {

        doNothing().when(deleteItemUseCase).delete(anyLong(), anyLong());

        this.mockMvc.perform(delete("/api/wishlist/client/1/item/342324"))
                .andExpect(status().isOk());
    }

}

