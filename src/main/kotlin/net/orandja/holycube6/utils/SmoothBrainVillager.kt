package net.orandja.holycube6.utils

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.brain.Activity
import net.minecraft.entity.ai.brain.Brain
import net.minecraft.entity.ai.brain.task.MultiTickTask
import net.minecraft.entity.ai.brain.task.Task
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.orandja.holycube6.accessor.BrainAccessor
import net.orandja.holycube6.modules.isWrench
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable

interface SmoothBrainVillager {

    var noAI: Boolean
    fun toggleAI() {
        this.noAI = !this.noAI
    }

    fun getSmoothBrain(): Brain<VillagerEntity>

    fun smoothBrainHandleAttack(entity: Any, attacker: Entity): Boolean {
        val player = attacker as? PlayerEntity ?: return false
        val villager = entity as? VillagerEntity ?: return false
        if (!player.mainHandStack.isWrench()) {
            return false
        }

        this.toggleAI()
        this.getSmoothBrain().stopAllTasks(entity.world as ServerWorld, villager)

        if (player is ServerPlayerEntity) {
            player.sendHUD(
                if (this.noAI) {
                    "Brain: Smooth"
                } else {
                    "Brain: Normal"
                }
            )
        }

        return true
    }

    @Suppress("UNCHECKED_CAST")
    fun <E : LivingEntity> tickSmoothBrain(realBrain: Brain<E>, world: ServerWorld, entity: E) {
        val brain = (realBrain as? BrainAccessor<E>) ?: return

        brain._tickMemories()
        brain._tickSensors(world, entity)
//        brain.startTasks(world, entity)
        this.startTasks(realBrain, brain, world, entity)
        brain._updateTasks(world, entity)
    }

    @Suppress("UNCHECKED_CAST")
    fun <E : LivingEntity> startTasks(realBrain: Brain<E>, brain: BrainAccessor<E>, world: ServerWorld, entity: E) {
        val l = world.time
        brain.tasks.values.map { it.entries }.forEach { tasks ->
            tasks.filter {
                (it.key === Activity.WORK || it.key === Activity.CORE) && realBrain.possibleActivities.contains(it.key)
            }.forEach {
                it.value.forEach { task ->
                    if(task.status == MultiTickTask.Status.STOPPED) {
                        task.tryStarting(world, entity, l)
                    }
                }
            }
        }
    }

    fun smoothBrainInteract(
        player: PlayerEntity,
        hand: Hand,
        info: CallbackInfoReturnable<ActionResult>
    ) {
        if (player.getStackInHand(hand).isWrench()) {
            if (player is ServerPlayerEntity) {
                player.sendHUD(
                    if (this.noAI) {
                        "Brain: Smooth"
                    } else {
                        "Brain: Normal"
                    }
                )
            }

            info.returnValue = ActionResult.SUCCESS
        }
    }

}