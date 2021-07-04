package br.com.wishlist.restapi;

import br.com.wishlist.ApiApplication;
import br.com.wishlist.restapi.model.request.ItemRequest;
import br.com.wishlist.usecase.AddItemUseCase;
import br.com.wishlist.usecase.DeleteItemUseCase;
import br.com.wishlist.usecase.FindItemsUseCase;
import br.com.wishlist.util.MockUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
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
                post("/api/wishlist/1/add")
                        .content(mockUtil.getJsonItemRequest())
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isCreated());
    }

    @Test
    public void given_ClientWithWishList_When_get_Then_ExpectedPageOfItems() throws Exception {

        when(findItemsUseCase.findAll(anyLong(), any())).thenReturn(mockUtil.getListOfValidItems());

        this.mockMvc.perform(
                get("/api/wishlist/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("size", "10")
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void given_ClientWithWishList_When_findByProductId_Then_ExpectedItemResponse() throws Exception {

        when(findItemsUseCase.findByProductId(anyLong(), anyLong())).thenReturn(mockUtil.getValidItem());

        this.mockMvc.perform(
                get("/api/wishlist/1/product/342324")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void given_ClientWithWishList_When_deleteProduct_Then_ExpectedItemDeleted() throws Exception {

        doNothing().when(deleteItemUseCase).deleteProduct(anyLong(), anyLong());

        this.mockMvc.perform(delete("/api/wishlist/1/product/342324"))
                .andExpect(status().isOk());
    }

}

