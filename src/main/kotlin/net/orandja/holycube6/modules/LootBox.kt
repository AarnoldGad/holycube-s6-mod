package net.orandja.holycube6.modules

import com.google.gson.JsonParser
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtIntArray
import net.minecraft.nbt.NbtList
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradeOfferList
import java.util.*

private enum class HeadMode {
    TEXTURE,
    VALUE;
}

private fun ItemStack.setHead(name: String, id: Any, texture: String, headMode: HeadMode = HeadMode.TEXTURE) = apply {
    this.orCreateNbt.apply {
        this.put("SkullOwner", NbtCompound().apply SkullOwner@{
            if (id is IntArray)
                this.put("Id", NbtIntArray(id))
            if (id is UUID)
                this.putUuid("Id", id)
            if(id is String)
                this.putUuid("Id", UUID.fromString(id))
            this.putString("Name", name)
            this.put("Properties", NbtCompound().apply Properties@{
                this.put("textures", NbtList().apply textures@{
                    this.add(NbtCompound().apply textureObject@{
                        when(headMode) {
                            HeadMode.TEXTURE -> this.putString("Value", Base64.getEncoder().encodeToString(("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/${texture}\"}}}").toByteArray()))
                            HeadMode.VALUE -> this.putString("Value", texture)
                        }
                    })
                })
            })
        })
    }
}

class LootBox {

    companion object {

        private val mainJSON = JsonParser.parseString(LootBox::class.java.classLoader.getResource("merchant.json").readText()).asJsonObject
        private val lootBoxes = mainJSON.getAsJsonArray("boxes")
            .map { it.asJsonArray }
            .map { box ->
                box.map { it.asJsonObject }.filter {
                    it.has("Name") && it.has("ID") && it.has("value")
                }.map {
                    if(it.get("ID").isJsonArray && it.get("ID").asJsonArray.size() == 4) {
                        ItemStack(Items.PLAYER_HEAD, 1).setHead( it.get("Name").asString, it.get("ID").asJsonArray.map { id -> id.asInt }.toIntArray(), it.get("value").asString, HeadMode.VALUE)
                    } else {
                        ItemStack(Items.PLAYER_HEAD, 1).setHead( it.get("Name").asString, UUID.fromString(it.get("ID").asString), it.get("value").asString, HeadMode.VALUE)
                    }
                }.toTypedArray()
            }

        private val singleHeads = mainJSON.getAsJsonArray("heads")
            .map { it.asJsonObject }
            .filter {
                it.has("Name") && it.has("ID") && it.has("value")
            }.map {
                if(it.get("ID").isJsonArray && it.get("ID").asJsonArray.size() == 4) {
                    ItemStack(Items.PLAYER_HEAD, 1).setHead( it.get("Name").asString, it.get("ID").asJsonArray.map { id -> id.asInt }.toIntArray(), it.get("value").asString, HeadMode.VALUE)
                } else {
                    ItemStack(Items.PLAYER_HEAD, 1).setHead( it.get("Name").asString, UUID.fromString(it.get("ID").asString), it.get("value").asString, HeadMode.VALUE)
                }
            }.toTypedArray()

        private fun getSingleHead(): ItemStack {
            return singleHeads[singleHeads.indices.random()]
        }

        private fun getLootBox(): ItemStack {
            return getLootBox(lootBoxes[lootBoxes.indices.random()])
        }

        private fun getLootBox(list: Array<ItemStack>): ItemStack {
            if (list.size > 27) {
                val randomList = ArrayList<ItemStack>()
                (0..26).forEach { _ ->
                    var stack = list[list.indices.random()]
                    while (randomList.contains(stack)) {
                        stack = list[list.indices.random()]
                    }
                    randomList.add(stack)
                }
                return getLootBox(randomList.toArray(Array(27) { ItemStack.EMPTY }))
            }

            return ItemStack(Items.BARREL, 1).apply {
                this.orCreateNbt.apply {
                    this.put("BlockEntityTag", NbtCompound().apply blockEntityTag@{
                        this.put("Items", NbtList().apply items@{
                            list.map { it.writeNbt(NbtCompound()) }.forEach {
                                it.putByte("Slot", this.size.toByte())
                                this.add(this.size, it)
                            }
                        })
                    })
                }
            }
        }

        fun hackOffers(offers: TradeOfferList) {
            when((0 until (lootBoxes.size + 2)).random()) {
                0 -> return
                //lootBoxes.size + 1 -> offers.add(0, TradeOffer(ItemStack(Items.DIAMOND, 10), ItemStack.EMPTY, getSingleHead(), 0, 99, 1, 1.0F))
                else -> offers.add(0, TradeOffer(ItemStack(Items.BARREL, 1), ItemStack(Items.DIAMOND, 1), getLootBox(), 0, 99, 1, 1.0F))
            }
        }
    }

}