package br.com.wishlist.mapper;

import br.com.wishlist.util.MockUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemMapperTest {

    @Test
    public void given_AValidDomainItem_when_mapFromDomain_ExpectedValidItemEntity() {
        var validDomainItem = MockUtil.getValidItem();
        var validEntityItem = ItemMapper.mapFromDomain(validDomainItem);

        assertNotNull(validEntityItem.getAddedAt());
        assertEquals(0L, validEntityItem.getBought());
        assertEquals(validDomainItem.getClientId(), validEntityItem.getClientId());
        assertEquals(validDomainItem.getDesired(), validEntityItem.getDesired());
        assertEquals(validDomainItem.getItemId(), validEntityItem.getItemId());
    }

    @Test
    public void given_AValidEntityItem_when_mapFromEntity_ExpectedValidItemDomain() {
        var validItemEntity = MockUtil.getValidItemEntity();
        var validDomainItem = ItemMapper.mapFromEntity(validItemEntity);

        assertNotNull(validDomainItem.getId());
        assertNotNull(validDomainItem.getAddedAt());
        assertEquals(validItemEntity.getBought(), validDomainItem.getBought());
        assertEquals(validItemEntity.getClientId(), validDomainItem.getClientId());
        assertEquals(validItemEntity.getDesired(), validDomainItem.getDesired());
        assertEquals(validItemEntity.getItemId(), validDomainItem.getItemId());
    }

}