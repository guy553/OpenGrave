package opengrave

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.Mod.InstanceFactory
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry

const val MODID = "opengrave"

@Mod(modid = MODID, modLanguage = "Kotlin")
object OpenGrave {

    @JvmStatic
    @InstanceFactory
    fun instanceFactory() = this

    @CapabilityInject(IDeathPositionProvider::class)
    var DEATH_CAPABILITY: Capability<IDeathPositionProvider>? = null

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent?) {
        debugLog.info("Opengrave preinit $event")

        GameRegistry.registerBlock(BlockGrave)
        GameRegistry.registerTileEntity(TileEntityGrave::class.java, TileEntityGrave.ID)

        GameRegistry.registerItem(ItemGraveCompass)

        MinecraftForge.EVENT_BUS.register(DeathHandler)
        MinecraftForge.EVENT_BUS.register(DeathCapabilityHandler)
        MinecraftForge.EVENT_BUS.register(RespawnHandler)

        DeathCapabilityHandler.registerCapability()

        debugPreInit(event)
    }
}