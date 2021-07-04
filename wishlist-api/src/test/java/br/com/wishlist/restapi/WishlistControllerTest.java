package br.com.wishlist.restapi;

import br.com.wishlist.ApiApplication;
import br.com.wishlist.usecase.AddItemUseCase;
import br.com.wishlist.usecase.DeleteItemUseCase;
import br.com.wishlist.usecase.FindItemsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(locations = "classpath:application.yml")
@WebMvcTest(WishlistController.class)
@Import(ApiApplication.class)
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

    @Test
    public void firstTest() throws Exception {
        this.mockMvc.perform(get("/api/wishlist/1")).andExpect(status().isOk());
    }

}

