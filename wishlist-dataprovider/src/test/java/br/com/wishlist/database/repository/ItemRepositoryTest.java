package br.com.wishlist.database.repository;

import br.com.wishlist.config.MongoConfig;
import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(properties = {
        "spring.data.mongodb.uri=mongodb://localhost:27017/wishlist"
})
@Import(MongoConfig.class)
@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Order(1)
    @Test
    public void given_AValidItemEntity_When_Save_Then_ExpectedItemSaved() {
        var validItemEntity = MockUtil.getValidItemEntity();
        var itemEntitySaved = itemRepository.save(validItemEntity);
        assertNotNull(itemEntitySaved.getId());
        assertNotNull(itemEntitySaved.getAddedAt());
        assertEquals(validItemEntity.getBought(), itemEntitySaved.getBought());
        assertEquals(validItemEntity.getClientId(), itemEntitySaved.getClientId());
        assertEquals(validItemEntity.getDesired(), itemEntitySaved.getDesired());
        assertEquals(validItemEntity.getItemId(), itemEntitySaved.getItemId());
    }

    @Order(2)
    @Test
    public void given_AClientWith1ItemInWishlist_When_CountByClientId_Then_Expected1() {
        var itemEntitySaved = itemRepository.countByClientId(MockUtil.getValidItemEntity().getClientId());
        assertEquals(1L, itemEntitySaved);
    }

    @Order(3)
    @Test
    public void given_AClientWith1ItemInWishlist_When_CountByClientIdAndItemId_Then_Expected1() {
        var validItemEntity = MockUtil.getValidItemEntity();

        var itemEntitySaved = itemRepository.countByClientIdAndItemId(validItemEntity.getClientId(),
                validItemEntity.getItemId());

        assertEquals(1L, itemEntitySaved);
    }

    @Order(4)
    @Test
    public void given_AClientWith1ItemInWishlist_When_FindByClientId_Then_ExpectedAListOfItems() {
        var validItemEntity = MockUtil.getValidItemEntity();

        var list = itemRepository.findByClientId(validItemEntity.getClientId(),
                PageRequest.of(0, 10));

        assertEquals(1L, list.size());
        assertNotNull(list.get(0).getId());
        assertNotNull(list.get(0).getAddedAt());
        assertEquals(validItemEntity.getBought(), list.get(0).getBought());
        assertEquals(validItemEntity.getClientId(), list.get(0).getClientId());
        assertEquals(validItemEntity.getDesired(), list.get(0).getDesired());
        assertEquals(validItemEntity.getItemId(), list.get(0).getItemId());
    }

    @Order(5)
    @Test
    public void given_AItemSavedInWishlist_When_deleteByClientIdAndItemId_Then_ExpectedItemDeleted() {
        var validItemEntity = MockUtil.getValidItemEntity();
        itemRepository.deleteByClientIdAndItemId(validItemEntity.getClientId(), validItemEntity.getItemId());
        var itemEntitySaved = itemRepository.countByClientIdAndItemId(validItemEntity.getClientId(),
                validItemEntity.getItemId());
        assertEquals(0L, itemEntitySaved);
    }



}