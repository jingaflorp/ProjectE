package moze_intel.projecte.gameObjs.items.armor;

import javax.annotation.Nonnull;
import moze_intel.projecte.PECore;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class DMArmor extends PEArmor {

	public DMArmor(EquipmentSlotType armorPiece, Properties props) {
		super(DMArmorMaterial.INSTANCE, armorPiece, props);
	}

	@Override
	public float getFullSetBaseReduction() {
		return 0.5F;
	}

	@Override
	public float getMaxDamageAbsorb(EquipmentSlotType slot, DamageSource source) {
		if (source.isExplosion()) {
			return 350;
		}
		if (slot == EquipmentSlotType.FEET && source == DamageSource.FALL) {
			return 5 / getPieceEffectiveness(slot);
		} else if (slot == EquipmentSlotType.HEAD && source == DamageSource.DROWN) {
			return 5 / getPieceEffectiveness(slot);
		}
		if (source.isBypassArmor()) {
			return 0;
		}
		//If the source is not unblockable, allow our piece to block a certain amount of damage
		if (slot == EquipmentSlotType.HEAD || slot == EquipmentSlotType.FEET) {
			return 100;
		}
		return 150;
	}

	private static class DMArmorMaterial implements IArmorMaterial {

		private static final DMArmorMaterial INSTANCE = new DMArmorMaterial();

		@Override
		public int getDurabilityForSlot(@Nonnull EquipmentSlotType slot) {
			return 0;
		}

		@Override
		public int getDefenseForSlot(@Nonnull EquipmentSlotType slot) {
			if (slot == EquipmentSlotType.FEET) {
				return 3;
			} else if (slot == EquipmentSlotType.LEGS) {
				return 6;
			} else if (slot == EquipmentSlotType.CHEST) {
				return 8;
			} else if (slot == EquipmentSlotType.HEAD) {
				return 3;
			}
			return 0;
		}

		@Override
		public int getEnchantmentValue() {
			return 0;
		}

		@Nonnull
		@Override
		public SoundEvent getEquipSound() {
			return SoundEvents.ARMOR_EQUIP_DIAMOND;
		}

		@Nonnull
		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.EMPTY;
		}

		@Nonnull
		@Override
		public String getName() {
			//Only used on the client
			return PECore.rl("dark_matter").toString();
		}

		@Override
		public float getToughness() {
			return 2;
		}

		@Override
		public float getKnockbackResistance() {
			return 0.1F;
		}
	}
}
