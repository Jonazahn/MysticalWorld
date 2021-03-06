package epicsquid.mysticalworld.entity.model.armor;

import epicsquid.mysticallib.client.model.ModelArmorBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ModelBeetleMask extends ModelArmorBase {
  public static ModelBeetleMask instance = new ModelBeetleMask();

  public ModelRenderer mask;
  public ModelRenderer horn;
  public ModelRenderer maskBottom;
  public ModelRenderer strap;
  public ModelRenderer horn2;

  public ModelBeetleMask() {
    super(EntityEquipmentSlot.HEAD);
    this.textureWidth = 64;
    this.textureHeight = 96;
    this.horn = new ModelRenderer(this, 1, 76);
    this.horn.setRotationPoint(0.0F, -8.5F, -4.5F);
    this.horn.addBox(-1.0F, -7.0F, 0.0F, 2, 7, 2, 0.0F);
    this.setRotateAngle(horn, -0.3141592653589793F, 0.0F, 0.0F);
    this.strap = new ModelRenderer(this, 15, 82);
    this.strap.setRotationPoint(0.0F, -4.0F, -2.5F);
    this.strap.addBox(-4.5F, -1.0F, 0.0F, 9, 2, 7, 0.0F);
    this.maskBottom = new ModelRenderer(this, 22, 64);
    this.maskBottom.setRotationPoint(0.0F, 0.5F, -3.5F);
    this.maskBottom.addBox(-2.5F, 0.0F, -1.0F, 5, 4, 2, 0.0F);
    this.mask = new ModelRenderer(this, 0, 64);
    this.mask.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.mask.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 2, 0.0F);
    this.horn2 = new ModelRenderer(this, 0, 85);
    this.horn2.setRotationPoint(0.0F, -6.0F, 2.0F);
    this.horn2.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
    this.mask.addChild(this.horn);
    this.mask.addChild(this.strap);
    this.mask.addChild(this.maskBottom);
    this.horn.addChild(this.horn2);
    this.head.addChild(this.mask);
  }

  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}

