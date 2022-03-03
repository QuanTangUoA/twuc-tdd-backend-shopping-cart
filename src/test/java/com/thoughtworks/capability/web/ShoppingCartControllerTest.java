package com.thoughtworks.capability.web;

import com.thoughtworks.capability.WebApplicationTest;
import com.thoughtworks.capability.service.ShoppingCartService;
import com.thoughtworks.capability.web.dto.ShoppingCartResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShoppingCartControllerTest extends WebApplicationTest {
    @MockBean
    public ShoppingCartService shoppingCartService;
    @Autowired
    private MockMvc mvc;
    @Test
    public void shouldReturnEmptyShoppingCartWhenHasNoProduct() throws Exception {
        //given

        Mockito.when(shoppingCartService.findShoppingCart()).thenReturn(new ShoppingCartResponse(Lists.emptyList()), BigDecimal.ZERO);
        //when
        mvc.perform(MockMvcRequestBuilders
                        .get("/shoppingCart"))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.totalAmount").value(BigDecimal.ZERO));
    }
}
