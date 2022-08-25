package com.example.tonyczk.Hellow_Mod;

import com.example.tonyczk.Hellow_Mod.inte.BlockInit;
import com.example.tonyczk.Hellow_Mod.inte.ItemInit;
import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


import static net.minecraftforge.versions.forge.ForgeVersion.MOD_ID;

@Mod("hello_mod")
@Mod.EventBusSubscriber
public class Main {

    public static final CreativeModeTab TUTORIAL_TAB = new CreativeModeTab(MOD_ID) {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(BlockInit.EXAMPLE_BLOCK.get());
        }

        public static final String MOD_ID = "hello_mod";

        };

        public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        //添加物品，方块的初始化信息
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

        public static void setup() {
            IEventBus bus = MinecraftForge.EVENT_BUS;
        }


        @SubscribeEvent
        public static void playerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
            Player player = event.getPlayer();
            Level level = player.level;

            player.sendMessage(new TextComponent("Hello " + player.getDisplayName().getString() + " from " + (level.isClientSide() ? "Client" : "Server" + ".")), Util.NIL_UUID);
        }

        @SubscribeEvent
        public static void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
            Player player = event.getPlayer();
            player.sendMessage(new TextComponent("LeftClickBlock is fire"), Util.NIL_UUID);
        }

        @SubscribeEvent
        public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
            Player player = event.getPlayer();
            player.sendMessage(new TextComponent("RightClickBlock is firenn"), Util.NIL_UUID);
        }

        @SubscribeEvent
        public static void rightClickItem(PlayerInteractEvent.RightClickItem event) {
            Player player = event.getPlayer();
            Level level = player.level;
            if (!level.isClientSide()) {
                ItemStack itemStack = event.getItemStack();
                Item item = itemStack.getItem();
                if (item == Items.DIAMOND_SWORD) {
                    HitResult hitResult = player.pick(20, 0, false);
                    Vec3 vec3 = hitResult.getLocation();
                    player.teleportTo(vec3.x, vec3.y, vec3.z);
                }
            }
        }
    }


