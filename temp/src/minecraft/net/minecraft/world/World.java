package net.minecraft.world;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldInfo;

public abstract class World implements IBlockAccess {
   private int field_181546_a = 63;
   protected boolean field_72999_e;
   public final List<Entity> field_72996_f = Lists.<Entity>newArrayList();
   protected final List<Entity> field_72997_g = Lists.<Entity>newArrayList();
   public final List<TileEntity> field_147482_g = Lists.<TileEntity>newArrayList();
   public final List<TileEntity> field_175730_i = Lists.<TileEntity>newArrayList();
   private final List<TileEntity> field_147484_a = Lists.<TileEntity>newArrayList();
   private final List<TileEntity> field_147483_b = Lists.<TileEntity>newArrayList();
   public final List<EntityPlayer> field_73010_i = Lists.<EntityPlayer>newArrayList();
   public final List<Entity> field_73007_j = Lists.<Entity>newArrayList();
   protected final IntHashMap<Entity> field_175729_l = new IntHashMap();
   private long field_73001_c = 16777215L;
   private int field_73008_k;
   protected int field_73005_l = (new Random()).nextInt();
   protected final int field_73006_m = 1013904223;
   protected float field_73003_n;
   protected float field_73004_o;
   protected float field_73018_p;
   protected float field_73017_q;
   private int field_73016_r;
   public final Random field_73012_v = new Random();
   public final WorldProvider field_73011_w;
   protected List<IWorldAccess> field_73021_x = Lists.<IWorldAccess>newArrayList();
   protected IChunkProvider field_73020_y;
   protected final ISaveHandler field_73019_z;
   protected WorldInfo field_72986_A;
   protected boolean field_72987_B;
   protected MapStorage field_72988_C;
   protected VillageCollection field_72982_D;
   public final Profiler field_72984_F;
   private final Calendar field_83016_L = Calendar.getInstance();
   protected Scoreboard field_96442_D = new Scoreboard();
   public final boolean field_72995_K;
   protected Set<ChunkCoordIntPair> field_72993_I = Sets.<ChunkCoordIntPair>newHashSet();
   private int field_72990_M;
   protected boolean field_72985_G;
   protected boolean field_72992_H;
   private boolean field_147481_N;
   private final WorldBorder field_175728_M;
   int[] field_72994_J;

   protected World(ISaveHandler p_i45749_1_, WorldInfo p_i45749_2_, WorldProvider p_i45749_3_, Profiler p_i45749_4_, boolean p_i45749_5_) {
      this.field_72990_M = this.field_73012_v.nextInt(12000);
      this.field_72985_G = true;
      this.field_72992_H = true;
      this.field_72994_J = new int['\u8000'];
      this.field_73019_z = p_i45749_1_;
      this.field_72984_F = p_i45749_4_;
      this.field_72986_A = p_i45749_2_;
      this.field_73011_w = p_i45749_3_;
      this.field_72995_K = p_i45749_5_;
      this.field_175728_M = p_i45749_3_.func_177501_r();
   }

   public World func_175643_b() {
      return this;
   }

   public BiomeGenBase func_180494_b(final BlockPos p_180494_1_) {
      if(this.func_175667_e(p_180494_1_)) {
         Chunk chunk = this.func_175726_f(p_180494_1_);

         try {
            return chunk.func_177411_a(p_180494_1_, this.field_73011_w.func_177499_m());
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Getting biome");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Coordinates of biome request");
            crashreportcategory.func_71500_a("Location", new Callable<String>() {
               public String call() throws Exception {
                  return CrashReportCategory.func_180522_a(p_180494_1_);
               }
            });
            throw new ReportedException(crashreport);
         }
      } else {
         return this.field_73011_w.func_177499_m().func_180300_a(p_180494_1_, BiomeGenBase.field_76772_c);
      }
   }

   public WorldChunkManager func_72959_q() {
      return this.field_73011_w.func_177499_m();
   }

   protected abstract IChunkProvider func_72970_h();

   public void func_72963_a(WorldSettings p_72963_1_) {
      this.field_72986_A.func_76091_d(true);
   }

   public void func_72974_f() {
      this.func_175652_B(new BlockPos(8, 64, 8));
   }

   public Block func_175703_c(BlockPos p_175703_1_) {
      BlockPos blockpos;
      for(blockpos = new BlockPos(p_175703_1_.func_177958_n(), this.func_181545_F(), p_175703_1_.func_177952_p()); !this.func_175623_d(blockpos.func_177984_a()); blockpos = blockpos.func_177984_a()) {
         ;
      }

      return this.func_180495_p(blockpos).func_177230_c();
   }

   private boolean func_175701_a(BlockPos p_175701_1_) {
      return p_175701_1_.func_177958_n() >= -30000000 && p_175701_1_.func_177952_p() >= -30000000 && p_175701_1_.func_177958_n() < 30000000 && p_175701_1_.func_177952_p() < 30000000 && p_175701_1_.func_177956_o() >= 0 && p_175701_1_.func_177956_o() < 256;
   }

   public boolean func_175623_d(BlockPos p_175623_1_) {
      return this.func_180495_p(p_175623_1_).func_177230_c().func_149688_o() == Material.field_151579_a;
   }

   public boolean func_175667_e(BlockPos p_175667_1_) {
      return this.func_175668_a(p_175667_1_, true);
   }

   public boolean func_175668_a(BlockPos p_175668_1_, boolean p_175668_2_) {
      return !this.func_175701_a(p_175668_1_)?false:this.func_175680_a(p_175668_1_.func_177958_n() >> 4, p_175668_1_.func_177952_p() >> 4, p_175668_2_);
   }

   public boolean func_175697_a(BlockPos p_175697_1_, int p_175697_2_) {
      return this.func_175648_a(p_175697_1_, p_175697_2_, true);
   }

   public boolean func_175648_a(BlockPos p_175648_1_, int p_175648_2_, boolean p_175648_3_) {
      return this.func_175663_a(p_175648_1_.func_177958_n() - p_175648_2_, p_175648_1_.func_177956_o() - p_175648_2_, p_175648_1_.func_177952_p() - p_175648_2_, p_175648_1_.func_177958_n() + p_175648_2_, p_175648_1_.func_177956_o() + p_175648_2_, p_175648_1_.func_177952_p() + p_175648_2_, p_175648_3_);
   }

   public boolean func_175707_a(BlockPos p_175707_1_, BlockPos p_175707_2_) {
      return this.func_175706_a(p_175707_1_, p_175707_2_, true);
   }

   public boolean func_175706_a(BlockPos p_175706_1_, BlockPos p_175706_2_, boolean p_175706_3_) {
      return this.func_175663_a(p_175706_1_.func_177958_n(), p_175706_1_.func_177956_o(), p_175706_1_.func_177952_p(), p_175706_2_.func_177958_n(), p_175706_2_.func_177956_o(), p_175706_2_.func_177952_p(), p_175706_3_);
   }

   public boolean func_175711_a(StructureBoundingBox p_175711_1_) {
      return this.func_175639_b(p_175711_1_, true);
   }

   public boolean func_175639_b(StructureBoundingBox p_175639_1_, boolean p_175639_2_) {
      return this.func_175663_a(p_175639_1_.field_78897_a, p_175639_1_.field_78895_b, p_175639_1_.field_78896_c, p_175639_1_.field_78893_d, p_175639_1_.field_78894_e, p_175639_1_.field_78892_f, p_175639_2_);
   }

   private boolean func_175663_a(int p_175663_1_, int p_175663_2_, int p_175663_3_, int p_175663_4_, int p_175663_5_, int p_175663_6_, boolean p_175663_7_) {
      if(p_175663_5_ >= 0 && p_175663_2_ < 256) {
         p_175663_1_ = p_175663_1_ >> 4;
         p_175663_3_ = p_175663_3_ >> 4;
         p_175663_4_ = p_175663_4_ >> 4;
         p_175663_6_ = p_175663_6_ >> 4;

         for(int i = p_175663_1_; i <= p_175663_4_; ++i) {
            for(int j = p_175663_3_; j <= p_175663_6_; ++j) {
               if(!this.func_175680_a(i, j, p_175663_7_)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   protected boolean func_175680_a(int p_175680_1_, int p_175680_2_, boolean p_175680_3_) {
      return this.field_73020_y.func_73149_a(p_175680_1_, p_175680_2_) && (p_175680_3_ || !this.field_73020_y.func_73154_d(p_175680_1_, p_175680_2_).func_76621_g());
   }

   public Chunk func_175726_f(BlockPos p_175726_1_) {
      return this.func_72964_e(p_175726_1_.func_177958_n() >> 4, p_175726_1_.func_177952_p() >> 4);
   }

   public Chunk func_72964_e(int p_72964_1_, int p_72964_2_) {
      return this.field_73020_y.func_73154_d(p_72964_1_, p_72964_2_);
   }

   public boolean func_180501_a(BlockPos p_180501_1_, IBlockState p_180501_2_, int p_180501_3_) {
      if(!this.func_175701_a(p_180501_1_)) {
         return false;
      } else if(!this.field_72995_K && this.field_72986_A.func_76067_t() == WorldType.field_180272_g) {
         return false;
      } else {
         Chunk chunk = this.func_175726_f(p_180501_1_);
         Block block = p_180501_2_.func_177230_c();
         IBlockState iblockstate = chunk.func_177436_a(p_180501_1_, p_180501_2_);
         if(iblockstate == null) {
            return false;
         } else {
            Block block1 = iblockstate.func_177230_c();
            if(block.func_149717_k() != block1.func_149717_k() || block.func_149750_m() != block1.func_149750_m()) {
               this.field_72984_F.func_76320_a("checkLight");
               this.func_175664_x(p_180501_1_);
               this.field_72984_F.func_76319_b();
            }

            if((p_180501_3_ & 2) != 0 && (!this.field_72995_K || (p_180501_3_ & 4) == 0) && chunk.func_150802_k()) {
               this.func_175689_h(p_180501_1_);
            }

            if(!this.field_72995_K && (p_180501_3_ & 1) != 0) {
               this.func_175722_b(p_180501_1_, iblockstate.func_177230_c());
               if(block.func_149740_M()) {
                  this.func_175666_e(p_180501_1_, block);
               }
            }

            return true;
         }
      }
   }

   public boolean func_175698_g(BlockPos p_175698_1_) {
      return this.func_180501_a(p_175698_1_, Blocks.field_150350_a.func_176223_P(), 3);
   }

   public boolean func_175655_b(BlockPos p_175655_1_, boolean p_175655_2_) {
      IBlockState iblockstate = this.func_180495_p(p_175655_1_);
      Block block = iblockstate.func_177230_c();
      if(block.func_149688_o() == Material.field_151579_a) {
         return false;
      } else {
         this.func_175718_b(2001, p_175655_1_, Block.func_176210_f(iblockstate));
         if(p_175655_2_) {
            block.func_176226_b(this, p_175655_1_, iblockstate, 0);
         }

         return this.func_180501_a(p_175655_1_, Blocks.field_150350_a.func_176223_P(), 3);
      }
   }

   public boolean func_175656_a(BlockPos p_175656_1_, IBlockState p_175656_2_) {
      return this.func_180501_a(p_175656_1_, p_175656_2_, 3);
   }

   public void func_175689_h(BlockPos p_175689_1_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_174960_a(p_175689_1_);
      }

   }

   public void func_175722_b(BlockPos p_175722_1_, Block p_175722_2_) {
      if(this.field_72986_A.func_76067_t() != WorldType.field_180272_g) {
         this.func_175685_c(p_175722_1_, p_175722_2_);
      }

   }

   public void func_72975_g(int p_72975_1_, int p_72975_2_, int p_72975_3_, int p_72975_4_) {
      if(p_72975_3_ > p_72975_4_) {
         int i = p_72975_4_;
         p_72975_4_ = p_72975_3_;
         p_72975_3_ = i;
      }

      if(!this.field_73011_w.func_177495_o()) {
         for(int j = p_72975_3_; j <= p_72975_4_; ++j) {
            this.func_180500_c(EnumSkyBlock.SKY, new BlockPos(p_72975_1_, j, p_72975_2_));
         }
      }

      this.func_147458_c(p_72975_1_, p_72975_3_, p_72975_2_, p_72975_1_, p_72975_4_, p_72975_2_);
   }

   public void func_175704_b(BlockPos p_175704_1_, BlockPos p_175704_2_) {
      this.func_147458_c(p_175704_1_.func_177958_n(), p_175704_1_.func_177956_o(), p_175704_1_.func_177952_p(), p_175704_2_.func_177958_n(), p_175704_2_.func_177956_o(), p_175704_2_.func_177952_p());
   }

   public void func_147458_c(int p_147458_1_, int p_147458_2_, int p_147458_3_, int p_147458_4_, int p_147458_5_, int p_147458_6_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_147585_a(p_147458_1_, p_147458_2_, p_147458_3_, p_147458_4_, p_147458_5_, p_147458_6_);
      }

   }

   public void func_175685_c(BlockPos p_175685_1_, Block p_175685_2_) {
      this.func_180496_d(p_175685_1_.func_177976_e(), p_175685_2_);
      this.func_180496_d(p_175685_1_.func_177974_f(), p_175685_2_);
      this.func_180496_d(p_175685_1_.func_177977_b(), p_175685_2_);
      this.func_180496_d(p_175685_1_.func_177984_a(), p_175685_2_);
      this.func_180496_d(p_175685_1_.func_177978_c(), p_175685_2_);
      this.func_180496_d(p_175685_1_.func_177968_d(), p_175685_2_);
   }

   public void func_175695_a(BlockPos p_175695_1_, Block p_175695_2_, EnumFacing p_175695_3_) {
      if(p_175695_3_ != EnumFacing.WEST) {
         this.func_180496_d(p_175695_1_.func_177976_e(), p_175695_2_);
      }

      if(p_175695_3_ != EnumFacing.EAST) {
         this.func_180496_d(p_175695_1_.func_177974_f(), p_175695_2_);
      }

      if(p_175695_3_ != EnumFacing.DOWN) {
         this.func_180496_d(p_175695_1_.func_177977_b(), p_175695_2_);
      }

      if(p_175695_3_ != EnumFacing.UP) {
         this.func_180496_d(p_175695_1_.func_177984_a(), p_175695_2_);
      }

      if(p_175695_3_ != EnumFacing.NORTH) {
         this.func_180496_d(p_175695_1_.func_177978_c(), p_175695_2_);
      }

      if(p_175695_3_ != EnumFacing.SOUTH) {
         this.func_180496_d(p_175695_1_.func_177968_d(), p_175695_2_);
      }

   }

   public void func_180496_d(BlockPos p_180496_1_, final Block p_180496_2_) {
      if(!this.field_72995_K) {
         IBlockState iblockstate = this.func_180495_p(p_180496_1_);

         try {
            iblockstate.func_177230_c().func_176204_a(this, p_180496_1_, iblockstate, p_180496_2_);
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Exception while updating neighbours");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Block being updated");
            crashreportcategory.func_71500_a("Source block type", new Callable<String>() {
               public String call() throws Exception {
                  try {
                     return String.format("ID #%d (%s // %s)", new Object[]{Integer.valueOf(Block.func_149682_b(p_180496_2_)), p_180496_2_.func_149739_a(), p_180496_2_.getClass().getCanonicalName()});
                  } catch (Throwable var2) {
                     return "ID #" + Block.func_149682_b(p_180496_2_);
                  }
               }
            });
            CrashReportCategory.func_175750_a(crashreportcategory, p_180496_1_, iblockstate);
            throw new ReportedException(crashreport);
         }
      }
   }

   public boolean func_175691_a(BlockPos p_175691_1_, Block p_175691_2_) {
      return false;
   }

   public boolean func_175678_i(BlockPos p_175678_1_) {
      return this.func_175726_f(p_175678_1_).func_177444_d(p_175678_1_);
   }

   public boolean func_175710_j(BlockPos p_175710_1_) {
      if(p_175710_1_.func_177956_o() >= this.func_181545_F()) {
         return this.func_175678_i(p_175710_1_);
      } else {
         BlockPos blockpos = new BlockPos(p_175710_1_.func_177958_n(), this.func_181545_F(), p_175710_1_.func_177952_p());
         if(!this.func_175678_i(blockpos)) {
            return false;
         } else {
            for(blockpos = blockpos.func_177977_b(); blockpos.func_177956_o() > p_175710_1_.func_177956_o(); blockpos = blockpos.func_177977_b()) {
               Block block = this.func_180495_p(blockpos).func_177230_c();
               if(block.func_149717_k() > 0 && !block.func_149688_o().func_76224_d()) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int func_175699_k(BlockPos p_175699_1_) {
      if(p_175699_1_.func_177956_o() < 0) {
         return 0;
      } else {
         if(p_175699_1_.func_177956_o() >= 256) {
            p_175699_1_ = new BlockPos(p_175699_1_.func_177958_n(), 255, p_175699_1_.func_177952_p());
         }

         return this.func_175726_f(p_175699_1_).func_177443_a(p_175699_1_, 0);
      }
   }

   public int func_175671_l(BlockPos p_175671_1_) {
      return this.func_175721_c(p_175671_1_, true);
   }

   public int func_175721_c(BlockPos p_175721_1_, boolean p_175721_2_) {
      if(p_175721_1_.func_177958_n() >= -30000000 && p_175721_1_.func_177952_p() >= -30000000 && p_175721_1_.func_177958_n() < 30000000 && p_175721_1_.func_177952_p() < 30000000) {
         if(p_175721_2_ && this.func_180495_p(p_175721_1_).func_177230_c().func_149710_n()) {
            int i1 = this.func_175721_c(p_175721_1_.func_177984_a(), false);
            int i = this.func_175721_c(p_175721_1_.func_177974_f(), false);
            int j = this.func_175721_c(p_175721_1_.func_177976_e(), false);
            int k = this.func_175721_c(p_175721_1_.func_177968_d(), false);
            int l = this.func_175721_c(p_175721_1_.func_177978_c(), false);
            if(i > i1) {
               i1 = i;
            }

            if(j > i1) {
               i1 = j;
            }

            if(k > i1) {
               i1 = k;
            }

            if(l > i1) {
               i1 = l;
            }

            return i1;
         } else if(p_175721_1_.func_177956_o() < 0) {
            return 0;
         } else {
            if(p_175721_1_.func_177956_o() >= 256) {
               p_175721_1_ = new BlockPos(p_175721_1_.func_177958_n(), 255, p_175721_1_.func_177952_p());
            }

            Chunk chunk = this.func_175726_f(p_175721_1_);
            return chunk.func_177443_a(p_175721_1_, this.field_73008_k);
         }
      } else {
         return 15;
      }
   }

   public BlockPos func_175645_m(BlockPos p_175645_1_) {
      int i;
      if(p_175645_1_.func_177958_n() >= -30000000 && p_175645_1_.func_177952_p() >= -30000000 && p_175645_1_.func_177958_n() < 30000000 && p_175645_1_.func_177952_p() < 30000000) {
         if(this.func_175680_a(p_175645_1_.func_177958_n() >> 4, p_175645_1_.func_177952_p() >> 4, true)) {
            i = this.func_72964_e(p_175645_1_.func_177958_n() >> 4, p_175645_1_.func_177952_p() >> 4).func_76611_b(p_175645_1_.func_177958_n() & 15, p_175645_1_.func_177952_p() & 15);
         } else {
            i = 0;
         }
      } else {
         i = this.func_181545_F() + 1;
      }

      return new BlockPos(p_175645_1_.func_177958_n(), i, p_175645_1_.func_177952_p());
   }

   public int func_82734_g(int p_82734_1_, int p_82734_2_) {
      if(p_82734_1_ >= -30000000 && p_82734_2_ >= -30000000 && p_82734_1_ < 30000000 && p_82734_2_ < 30000000) {
         if(!this.func_175680_a(p_82734_1_ >> 4, p_82734_2_ >> 4, true)) {
            return 0;
         } else {
            Chunk chunk = this.func_72964_e(p_82734_1_ >> 4, p_82734_2_ >> 4);
            return chunk.func_177442_v();
         }
      } else {
         return this.func_181545_F() + 1;
      }
   }

   public int func_175705_a(EnumSkyBlock p_175705_1_, BlockPos p_175705_2_) {
      if(this.field_73011_w.func_177495_o() && p_175705_1_ == EnumSkyBlock.SKY) {
         return 0;
      } else {
         if(p_175705_2_.func_177956_o() < 0) {
            p_175705_2_ = new BlockPos(p_175705_2_.func_177958_n(), 0, p_175705_2_.func_177952_p());
         }

         if(!this.func_175701_a(p_175705_2_)) {
            return p_175705_1_.field_77198_c;
         } else if(!this.func_175667_e(p_175705_2_)) {
            return p_175705_1_.field_77198_c;
         } else if(this.func_180495_p(p_175705_2_).func_177230_c().func_149710_n()) {
            int i1 = this.func_175642_b(p_175705_1_, p_175705_2_.func_177984_a());
            int i = this.func_175642_b(p_175705_1_, p_175705_2_.func_177974_f());
            int j = this.func_175642_b(p_175705_1_, p_175705_2_.func_177976_e());
            int k = this.func_175642_b(p_175705_1_, p_175705_2_.func_177968_d());
            int l = this.func_175642_b(p_175705_1_, p_175705_2_.func_177978_c());
            if(i > i1) {
               i1 = i;
            }

            if(j > i1) {
               i1 = j;
            }

            if(k > i1) {
               i1 = k;
            }

            if(l > i1) {
               i1 = l;
            }

            return i1;
         } else {
            Chunk chunk = this.func_175726_f(p_175705_2_);
            return chunk.func_177413_a(p_175705_1_, p_175705_2_);
         }
      }
   }

   public int func_175642_b(EnumSkyBlock p_175642_1_, BlockPos p_175642_2_) {
      if(p_175642_2_.func_177956_o() < 0) {
         p_175642_2_ = new BlockPos(p_175642_2_.func_177958_n(), 0, p_175642_2_.func_177952_p());
      }

      if(!this.func_175701_a(p_175642_2_)) {
         return p_175642_1_.field_77198_c;
      } else if(!this.func_175667_e(p_175642_2_)) {
         return p_175642_1_.field_77198_c;
      } else {
         Chunk chunk = this.func_175726_f(p_175642_2_);
         return chunk.func_177413_a(p_175642_1_, p_175642_2_);
      }
   }

   public void func_175653_a(EnumSkyBlock p_175653_1_, BlockPos p_175653_2_, int p_175653_3_) {
      if(this.func_175701_a(p_175653_2_)) {
         if(this.func_175667_e(p_175653_2_)) {
            Chunk chunk = this.func_175726_f(p_175653_2_);
            chunk.func_177431_a(p_175653_1_, p_175653_2_, p_175653_3_);
            this.func_175679_n(p_175653_2_);
         }
      }
   }

   public void func_175679_n(BlockPos p_175679_1_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_174959_b(p_175679_1_);
      }

   }

   public int func_175626_b(BlockPos p_175626_1_, int p_175626_2_) {
      int i = this.func_175705_a(EnumSkyBlock.SKY, p_175626_1_);
      int j = this.func_175705_a(EnumSkyBlock.BLOCK, p_175626_1_);
      if(j < p_175626_2_) {
         j = p_175626_2_;
      }

      return i << 20 | j << 4;
   }

   public float func_175724_o(BlockPos p_175724_1_) {
      return this.field_73011_w.func_177497_p()[this.func_175671_l(p_175724_1_)];
   }

   public IBlockState func_180495_p(BlockPos p_180495_1_) {
      if(!this.func_175701_a(p_180495_1_)) {
         return Blocks.field_150350_a.func_176223_P();
      } else {
         Chunk chunk = this.func_175726_f(p_180495_1_);
         return chunk.func_177435_g(p_180495_1_);
      }
   }

   public boolean func_72935_r() {
      return this.field_73008_k < 4;
   }

   public MovingObjectPosition func_72933_a(Vec3 p_72933_1_, Vec3 p_72933_2_) {
      return this.func_147447_a(p_72933_1_, p_72933_2_, false, false, false);
   }

   public MovingObjectPosition func_72901_a(Vec3 p_72901_1_, Vec3 p_72901_2_, boolean p_72901_3_) {
      return this.func_147447_a(p_72901_1_, p_72901_2_, p_72901_3_, false, false);
   }

   public MovingObjectPosition func_147447_a(Vec3 p_147447_1_, Vec3 p_147447_2_, boolean p_147447_3_, boolean p_147447_4_, boolean p_147447_5_) {
      if(!Double.isNaN(p_147447_1_.field_72450_a) && !Double.isNaN(p_147447_1_.field_72448_b) && !Double.isNaN(p_147447_1_.field_72449_c)) {
         if(!Double.isNaN(p_147447_2_.field_72450_a) && !Double.isNaN(p_147447_2_.field_72448_b) && !Double.isNaN(p_147447_2_.field_72449_c)) {
            int i = MathHelper.func_76128_c(p_147447_2_.field_72450_a);
            int j = MathHelper.func_76128_c(p_147447_2_.field_72448_b);
            int k = MathHelper.func_76128_c(p_147447_2_.field_72449_c);
            int l = MathHelper.func_76128_c(p_147447_1_.field_72450_a);
            int i1 = MathHelper.func_76128_c(p_147447_1_.field_72448_b);
            int j1 = MathHelper.func_76128_c(p_147447_1_.field_72449_c);
            BlockPos blockpos = new BlockPos(l, i1, j1);
            IBlockState iblockstate = this.func_180495_p(blockpos);
            Block block = iblockstate.func_177230_c();
            if((!p_147447_4_ || block.func_180640_a(this, blockpos, iblockstate) != null) && block.func_176209_a(iblockstate, p_147447_3_)) {
               MovingObjectPosition movingobjectposition = block.func_180636_a(this, blockpos, p_147447_1_, p_147447_2_);
               if(movingobjectposition != null) {
                  return movingobjectposition;
               }
            }

            MovingObjectPosition movingobjectposition2 = null;
            int k1 = 200;

            while(k1-- >= 0) {
               if(Double.isNaN(p_147447_1_.field_72450_a) || Double.isNaN(p_147447_1_.field_72448_b) || Double.isNaN(p_147447_1_.field_72449_c)) {
                  return null;
               }

               if(l == i && i1 == j && j1 == k) {
                  return p_147447_5_?movingobjectposition2:null;
               }

               boolean flag2 = true;
               boolean flag = true;
               boolean flag1 = true;
               double d0 = 999.0D;
               double d1 = 999.0D;
               double d2 = 999.0D;
               if(i > l) {
                  d0 = (double)l + 1.0D;
               } else if(i < l) {
                  d0 = (double)l + 0.0D;
               } else {
                  flag2 = false;
               }

               if(j > i1) {
                  d1 = (double)i1 + 1.0D;
               } else if(j < i1) {
                  d1 = (double)i1 + 0.0D;
               } else {
                  flag = false;
               }

               if(k > j1) {
                  d2 = (double)j1 + 1.0D;
               } else if(k < j1) {
                  d2 = (double)j1 + 0.0D;
               } else {
                  flag1 = false;
               }

               double d3 = 999.0D;
               double d4 = 999.0D;
               double d5 = 999.0D;
               double d6 = p_147447_2_.field_72450_a - p_147447_1_.field_72450_a;
               double d7 = p_147447_2_.field_72448_b - p_147447_1_.field_72448_b;
               double d8 = p_147447_2_.field_72449_c - p_147447_1_.field_72449_c;
               if(flag2) {
                  d3 = (d0 - p_147447_1_.field_72450_a) / d6;
               }

               if(flag) {
                  d4 = (d1 - p_147447_1_.field_72448_b) / d7;
               }

               if(flag1) {
                  d5 = (d2 - p_147447_1_.field_72449_c) / d8;
               }

               if(d3 == -0.0D) {
                  d3 = -1.0E-4D;
               }

               if(d4 == -0.0D) {
                  d4 = -1.0E-4D;
               }

               if(d5 == -0.0D) {
                  d5 = -1.0E-4D;
               }

               EnumFacing enumfacing;
               if(d3 < d4 && d3 < d5) {
                  enumfacing = i > l?EnumFacing.WEST:EnumFacing.EAST;
                  p_147447_1_ = new Vec3(d0, p_147447_1_.field_72448_b + d7 * d3, p_147447_1_.field_72449_c + d8 * d3);
               } else if(d4 < d5) {
                  enumfacing = j > i1?EnumFacing.DOWN:EnumFacing.UP;
                  p_147447_1_ = new Vec3(p_147447_1_.field_72450_a + d6 * d4, d1, p_147447_1_.field_72449_c + d8 * d4);
               } else {
                  enumfacing = k > j1?EnumFacing.NORTH:EnumFacing.SOUTH;
                  p_147447_1_ = new Vec3(p_147447_1_.field_72450_a + d6 * d5, p_147447_1_.field_72448_b + d7 * d5, d2);
               }

               l = MathHelper.func_76128_c(p_147447_1_.field_72450_a) - (enumfacing == EnumFacing.EAST?1:0);
               i1 = MathHelper.func_76128_c(p_147447_1_.field_72448_b) - (enumfacing == EnumFacing.UP?1:0);
               j1 = MathHelper.func_76128_c(p_147447_1_.field_72449_c) - (enumfacing == EnumFacing.SOUTH?1:0);
               blockpos = new BlockPos(l, i1, j1);
               IBlockState iblockstate1 = this.func_180495_p(blockpos);
               Block block1 = iblockstate1.func_177230_c();
               if(!p_147447_4_ || block1.func_180640_a(this, blockpos, iblockstate1) != null) {
                  if(block1.func_176209_a(iblockstate1, p_147447_3_)) {
                     MovingObjectPosition movingobjectposition1 = block1.func_180636_a(this, blockpos, p_147447_1_, p_147447_2_);
                     if(movingobjectposition1 != null) {
                        return movingobjectposition1;
                     }
                  } else {
                     movingobjectposition2 = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, p_147447_1_, enumfacing, blockpos);
                  }
               }
            }

            return p_147447_5_?movingobjectposition2:null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public void func_72956_a(Entity p_72956_1_, String p_72956_2_, float p_72956_3_, float p_72956_4_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_72704_a(p_72956_2_, p_72956_1_.field_70165_t, p_72956_1_.field_70163_u, p_72956_1_.field_70161_v, p_72956_3_, p_72956_4_);
      }

   }

   public void func_85173_a(EntityPlayer p_85173_1_, String p_85173_2_, float p_85173_3_, float p_85173_4_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_85102_a(p_85173_1_, p_85173_2_, p_85173_1_.field_70165_t, p_85173_1_.field_70163_u, p_85173_1_.field_70161_v, p_85173_3_, p_85173_4_);
      }

   }

   public void func_72908_a(double p_72908_1_, double p_72908_3_, double p_72908_5_, String p_72908_7_, float p_72908_8_, float p_72908_9_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_72704_a(p_72908_7_, p_72908_1_, p_72908_3_, p_72908_5_, p_72908_8_, p_72908_9_);
      }

   }

   public void func_72980_b(double p_72980_1_, double p_72980_3_, double p_72980_5_, String p_72980_7_, float p_72980_8_, float p_72980_9_, boolean p_72980_10_) {
   }

   public void func_175717_a(BlockPos p_175717_1_, String p_175717_2_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_174961_a(p_175717_2_, p_175717_1_);
      }

   }

   public void func_175688_a(EnumParticleTypes p_175688_1_, double p_175688_2_, double p_175688_4_, double p_175688_6_, double p_175688_8_, double p_175688_10_, double p_175688_12_, int... p_175688_14_) {
      this.func_175720_a(p_175688_1_.func_179348_c(), p_175688_1_.func_179344_e(), p_175688_2_, p_175688_4_, p_175688_6_, p_175688_8_, p_175688_10_, p_175688_12_, p_175688_14_);
   }

   public void func_175682_a(EnumParticleTypes p_175682_1_, boolean p_175682_2_, double p_175682_3_, double p_175682_5_, double p_175682_7_, double p_175682_9_, double p_175682_11_, double p_175682_13_, int... p_175682_15_) {
      this.func_175720_a(p_175682_1_.func_179348_c(), p_175682_1_.func_179344_e() | p_175682_2_, p_175682_3_, p_175682_5_, p_175682_7_, p_175682_9_, p_175682_11_, p_175682_13_, p_175682_15_);
   }

   private void func_175720_a(int p_175720_1_, boolean p_175720_2_, double p_175720_3_, double p_175720_5_, double p_175720_7_, double p_175720_9_, double p_175720_11_, double p_175720_13_, int... p_175720_15_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_180442_a(p_175720_1_, p_175720_2_, p_175720_3_, p_175720_5_, p_175720_7_, p_175720_9_, p_175720_11_, p_175720_13_, p_175720_15_);
      }

   }

   public boolean func_72942_c(Entity p_72942_1_) {
      this.field_73007_j.add(p_72942_1_);
      return true;
   }

   public boolean func_72838_d(Entity p_72838_1_) {
      int i = MathHelper.func_76128_c(p_72838_1_.field_70165_t / 16.0D);
      int j = MathHelper.func_76128_c(p_72838_1_.field_70161_v / 16.0D);
      boolean flag = p_72838_1_.field_98038_p;
      if(p_72838_1_ instanceof EntityPlayer) {
         flag = true;
      }

      if(!flag && !this.func_175680_a(i, j, true)) {
         return false;
      } else {
         if(p_72838_1_ instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)p_72838_1_;
            this.field_73010_i.add(entityplayer);
            this.func_72854_c();
         }

         this.func_72964_e(i, j).func_76612_a(p_72838_1_);
         this.field_72996_f.add(p_72838_1_);
         this.func_72923_a(p_72838_1_);
         return true;
      }
   }

   protected void func_72923_a(Entity p_72923_1_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_72703_a(p_72923_1_);
      }

   }

   protected void func_72847_b(Entity p_72847_1_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_72709_b(p_72847_1_);
      }

   }

   public void func_72900_e(Entity p_72900_1_) {
      if(p_72900_1_.field_70153_n != null) {
         p_72900_1_.field_70153_n.func_70078_a((Entity)null);
      }

      if(p_72900_1_.field_70154_o != null) {
         p_72900_1_.func_70078_a((Entity)null);
      }

      p_72900_1_.func_70106_y();
      if(p_72900_1_ instanceof EntityPlayer) {
         this.field_73010_i.remove(p_72900_1_);
         this.func_72854_c();
         this.func_72847_b(p_72900_1_);
      }

   }

   public void func_72973_f(Entity p_72973_1_) {
      p_72973_1_.func_70106_y();
      if(p_72973_1_ instanceof EntityPlayer) {
         this.field_73010_i.remove(p_72973_1_);
         this.func_72854_c();
      }

      int i = p_72973_1_.field_70176_ah;
      int j = p_72973_1_.field_70164_aj;
      if(p_72973_1_.field_70175_ag && this.func_175680_a(i, j, true)) {
         this.func_72964_e(i, j).func_76622_b(p_72973_1_);
      }

      this.field_72996_f.remove(p_72973_1_);
      this.func_72847_b(p_72973_1_);
   }

   public void func_72954_a(IWorldAccess p_72954_1_) {
      this.field_73021_x.add(p_72954_1_);
   }

   public void func_72848_b(IWorldAccess p_72848_1_) {
      this.field_73021_x.remove(p_72848_1_);
   }

   public List<AxisAlignedBB> func_72945_a(Entity p_72945_1_, AxisAlignedBB p_72945_2_) {
      List<AxisAlignedBB> list = Lists.<AxisAlignedBB>newArrayList();
      int i = MathHelper.func_76128_c(p_72945_2_.field_72340_a);
      int j = MathHelper.func_76128_c(p_72945_2_.field_72336_d + 1.0D);
      int k = MathHelper.func_76128_c(p_72945_2_.field_72338_b);
      int l = MathHelper.func_76128_c(p_72945_2_.field_72337_e + 1.0D);
      int i1 = MathHelper.func_76128_c(p_72945_2_.field_72339_c);
      int j1 = MathHelper.func_76128_c(p_72945_2_.field_72334_f + 1.0D);
      WorldBorder worldborder = this.func_175723_af();
      boolean flag = p_72945_1_.func_174832_aS();
      boolean flag1 = this.func_175673_a(worldborder, p_72945_1_);
      IBlockState iblockstate = Blocks.field_150348_b.func_176223_P();
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int k1 = i; k1 < j; ++k1) {
         for(int l1 = i1; l1 < j1; ++l1) {
            if(this.func_175667_e(blockpos$mutableblockpos.func_181079_c(k1, 64, l1))) {
               for(int i2 = k - 1; i2 < l; ++i2) {
                  blockpos$mutableblockpos.func_181079_c(k1, i2, l1);
                  if(flag && flag1) {
                     p_72945_1_.func_174821_h(false);
                  } else if(!flag && !flag1) {
                     p_72945_1_.func_174821_h(true);
                  }

                  IBlockState iblockstate1 = iblockstate;
                  if(worldborder.func_177746_a(blockpos$mutableblockpos) || !flag1) {
                     iblockstate1 = this.func_180495_p(blockpos$mutableblockpos);
                  }

                  iblockstate1.func_177230_c().func_180638_a(this, blockpos$mutableblockpos, iblockstate1, p_72945_2_, list, p_72945_1_);
               }
            }
         }
      }

      double d0 = 0.25D;
      List<Entity> list1 = this.func_72839_b(p_72945_1_, p_72945_2_.func_72314_b(d0, d0, d0));

      for(int j2 = 0; j2 < list1.size(); ++j2) {
         if(p_72945_1_.field_70153_n != list1 && p_72945_1_.field_70154_o != list1) {
            AxisAlignedBB axisalignedbb = ((Entity)list1.get(j2)).func_70046_E();
            if(axisalignedbb != null && axisalignedbb.func_72326_a(p_72945_2_)) {
               list.add(axisalignedbb);
            }

            axisalignedbb = p_72945_1_.func_70114_g((Entity)list1.get(j2));
            if(axisalignedbb != null && axisalignedbb.func_72326_a(p_72945_2_)) {
               list.add(axisalignedbb);
            }
         }
      }

      return list;
   }

   public boolean func_175673_a(WorldBorder p_175673_1_, Entity p_175673_2_) {
      double d0 = p_175673_1_.func_177726_b();
      double d1 = p_175673_1_.func_177736_c();
      double d2 = p_175673_1_.func_177728_d();
      double d3 = p_175673_1_.func_177733_e();
      if(p_175673_2_.func_174832_aS()) {
         ++d0;
         ++d1;
         --d2;
         --d3;
      } else {
         --d0;
         --d1;
         ++d2;
         ++d3;
      }

      return p_175673_2_.field_70165_t > d0 && p_175673_2_.field_70165_t < d2 && p_175673_2_.field_70161_v > d1 && p_175673_2_.field_70161_v < d3;
   }

   public List<AxisAlignedBB> func_147461_a(AxisAlignedBB p_147461_1_) {
      List<AxisAlignedBB> list = Lists.<AxisAlignedBB>newArrayList();
      int i = MathHelper.func_76128_c(p_147461_1_.field_72340_a);
      int j = MathHelper.func_76128_c(p_147461_1_.field_72336_d + 1.0D);
      int k = MathHelper.func_76128_c(p_147461_1_.field_72338_b);
      int l = MathHelper.func_76128_c(p_147461_1_.field_72337_e + 1.0D);
      int i1 = MathHelper.func_76128_c(p_147461_1_.field_72339_c);
      int j1 = MathHelper.func_76128_c(p_147461_1_.field_72334_f + 1.0D);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int k1 = i; k1 < j; ++k1) {
         for(int l1 = i1; l1 < j1; ++l1) {
            if(this.func_175667_e(blockpos$mutableblockpos.func_181079_c(k1, 64, l1))) {
               for(int i2 = k - 1; i2 < l; ++i2) {
                  blockpos$mutableblockpos.func_181079_c(k1, i2, l1);
                  IBlockState iblockstate;
                  if(k1 >= -30000000 && k1 < 30000000 && l1 >= -30000000 && l1 < 30000000) {
                     iblockstate = this.func_180495_p(blockpos$mutableblockpos);
                  } else {
                     iblockstate = Blocks.field_150357_h.func_176223_P();
                  }

                  iblockstate.func_177230_c().func_180638_a(this, blockpos$mutableblockpos, iblockstate, p_147461_1_, list, (Entity)null);
               }
            }
         }
      }

      return list;
   }

   public int func_72967_a(float p_72967_1_) {
      float f = this.func_72826_c(p_72967_1_);
      float f1 = 1.0F - (MathHelper.func_76134_b(f * 3.1415927F * 2.0F) * 2.0F + 0.5F);
      f1 = MathHelper.func_76131_a(f1, 0.0F, 1.0F);
      f1 = 1.0F - f1;
      f1 = (float)((double)f1 * (1.0D - (double)(this.func_72867_j(p_72967_1_) * 5.0F) / 16.0D));
      f1 = (float)((double)f1 * (1.0D - (double)(this.func_72819_i(p_72967_1_) * 5.0F) / 16.0D));
      f1 = 1.0F - f1;
      return (int)(f1 * 11.0F);
   }

   public float func_72971_b(float p_72971_1_) {
      float f = this.func_72826_c(p_72971_1_);
      float f1 = 1.0F - (MathHelper.func_76134_b(f * 3.1415927F * 2.0F) * 2.0F + 0.2F);
      f1 = MathHelper.func_76131_a(f1, 0.0F, 1.0F);
      f1 = 1.0F - f1;
      f1 = (float)((double)f1 * (1.0D - (double)(this.func_72867_j(p_72971_1_) * 5.0F) / 16.0D));
      f1 = (float)((double)f1 * (1.0D - (double)(this.func_72819_i(p_72971_1_) * 5.0F) / 16.0D));
      return f1 * 0.8F + 0.2F;
   }

   public Vec3 func_72833_a(Entity p_72833_1_, float p_72833_2_) {
      float f = this.func_72826_c(p_72833_2_);
      float f1 = MathHelper.func_76134_b(f * 3.1415927F * 2.0F) * 2.0F + 0.5F;
      f1 = MathHelper.func_76131_a(f1, 0.0F, 1.0F);
      int i = MathHelper.func_76128_c(p_72833_1_.field_70165_t);
      int j = MathHelper.func_76128_c(p_72833_1_.field_70163_u);
      int k = MathHelper.func_76128_c(p_72833_1_.field_70161_v);
      BlockPos blockpos = new BlockPos(i, j, k);
      BiomeGenBase biomegenbase = this.func_180494_b(blockpos);
      float f2 = biomegenbase.func_180626_a(blockpos);
      int l = biomegenbase.func_76731_a(f2);
      float f3 = (float)(l >> 16 & 255) / 255.0F;
      float f4 = (float)(l >> 8 & 255) / 255.0F;
      float f5 = (float)(l & 255) / 255.0F;
      f3 = f3 * f1;
      f4 = f4 * f1;
      f5 = f5 * f1;
      float f6 = this.func_72867_j(p_72833_2_);
      if(f6 > 0.0F) {
         float f7 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.6F;
         float f8 = 1.0F - f6 * 0.75F;
         f3 = f3 * f8 + f7 * (1.0F - f8);
         f4 = f4 * f8 + f7 * (1.0F - f8);
         f5 = f5 * f8 + f7 * (1.0F - f8);
      }

      float f10 = this.func_72819_i(p_72833_2_);
      if(f10 > 0.0F) {
         float f11 = (f3 * 0.3F + f4 * 0.59F + f5 * 0.11F) * 0.2F;
         float f9 = 1.0F - f10 * 0.75F;
         f3 = f3 * f9 + f11 * (1.0F - f9);
         f4 = f4 * f9 + f11 * (1.0F - f9);
         f5 = f5 * f9 + f11 * (1.0F - f9);
      }

      if(this.field_73016_r > 0) {
         float f12 = (float)this.field_73016_r - p_72833_2_;
         if(f12 > 1.0F) {
            f12 = 1.0F;
         }

         f12 = f12 * 0.45F;
         f3 = f3 * (1.0F - f12) + 0.8F * f12;
         f4 = f4 * (1.0F - f12) + 0.8F * f12;
         f5 = f5 * (1.0F - f12) + 1.0F * f12;
      }

      return new Vec3((double)f3, (double)f4, (double)f5);
   }

   public float func_72826_c(float p_72826_1_) {
      return this.field_73011_w.func_76563_a(this.field_72986_A.func_76073_f(), p_72826_1_);
   }

   public int func_72853_d() {
      return this.field_73011_w.func_76559_b(this.field_72986_A.func_76073_f());
   }

   public float func_130001_d() {
      return WorldProvider.field_111203_a[this.field_73011_w.func_76559_b(this.field_72986_A.func_76073_f())];
   }

   public float func_72929_e(float p_72929_1_) {
      float f = this.func_72826_c(p_72929_1_);
      return f * 3.1415927F * 2.0F;
   }

   public Vec3 func_72824_f(float p_72824_1_) {
      float f = this.func_72826_c(p_72824_1_);
      float f1 = MathHelper.func_76134_b(f * 3.1415927F * 2.0F) * 2.0F + 0.5F;
      f1 = MathHelper.func_76131_a(f1, 0.0F, 1.0F);
      float f2 = (float)(this.field_73001_c >> 16 & 255L) / 255.0F;
      float f3 = (float)(this.field_73001_c >> 8 & 255L) / 255.0F;
      float f4 = (float)(this.field_73001_c & 255L) / 255.0F;
      float f5 = this.func_72867_j(p_72824_1_);
      if(f5 > 0.0F) {
         float f6 = (f2 * 0.3F + f3 * 0.59F + f4 * 0.11F) * 0.6F;
         float f7 = 1.0F - f5 * 0.95F;
         f2 = f2 * f7 + f6 * (1.0F - f7);
         f3 = f3 * f7 + f6 * (1.0F - f7);
         f4 = f4 * f7 + f6 * (1.0F - f7);
      }

      f2 = f2 * (f1 * 0.9F + 0.1F);
      f3 = f3 * (f1 * 0.9F + 0.1F);
      f4 = f4 * (f1 * 0.85F + 0.15F);
      float f9 = this.func_72819_i(p_72824_1_);
      if(f9 > 0.0F) {
         float f10 = (f2 * 0.3F + f3 * 0.59F + f4 * 0.11F) * 0.2F;
         float f8 = 1.0F - f9 * 0.95F;
         f2 = f2 * f8 + f10 * (1.0F - f8);
         f3 = f3 * f8 + f10 * (1.0F - f8);
         f4 = f4 * f8 + f10 * (1.0F - f8);
      }

      return new Vec3((double)f2, (double)f3, (double)f4);
   }

   public Vec3 func_72948_g(float p_72948_1_) {
      float f = this.func_72826_c(p_72948_1_);
      return this.field_73011_w.func_76562_b(f, p_72948_1_);
   }

   public BlockPos func_175725_q(BlockPos p_175725_1_) {
      return this.func_175726_f(p_175725_1_).func_177440_h(p_175725_1_);
   }

   public BlockPos func_175672_r(BlockPos p_175672_1_) {
      Chunk chunk = this.func_175726_f(p_175672_1_);

      BlockPos blockpos;
      BlockPos blockpos1;
      for(blockpos = new BlockPos(p_175672_1_.func_177958_n(), chunk.func_76625_h() + 16, p_175672_1_.func_177952_p()); blockpos.func_177956_o() >= 0; blockpos = blockpos1) {
         blockpos1 = blockpos.func_177977_b();
         Material material = chunk.func_177428_a(blockpos1).func_149688_o();
         if(material.func_76230_c() && material != Material.field_151584_j) {
            break;
         }
      }

      return blockpos;
   }

   public float func_72880_h(float p_72880_1_) {
      float f = this.func_72826_c(p_72880_1_);
      float f1 = 1.0F - (MathHelper.func_76134_b(f * 3.1415927F * 2.0F) * 2.0F + 0.25F);
      f1 = MathHelper.func_76131_a(f1, 0.0F, 1.0F);
      return f1 * f1 * 0.5F;
   }

   public void func_175684_a(BlockPos p_175684_1_, Block p_175684_2_, int p_175684_3_) {
   }

   public void func_175654_a(BlockPos p_175654_1_, Block p_175654_2_, int p_175654_3_, int p_175654_4_) {
   }

   public void func_180497_b(BlockPos p_180497_1_, Block p_180497_2_, int p_180497_3_, int p_180497_4_) {
   }

   public void func_72939_s() {
      this.field_72984_F.func_76320_a("entities");
      this.field_72984_F.func_76320_a("global");

      for(int i = 0; i < this.field_73007_j.size(); ++i) {
         Entity entity = (Entity)this.field_73007_j.get(i);

         try {
            ++entity.field_70173_aa;
            entity.func_70071_h_();
         } catch (Throwable throwable2) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable2, "Ticking entity");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Entity being ticked");
            if(entity == null) {
               crashreportcategory.func_71507_a("Entity", "~~NULL~~");
            } else {
               entity.func_85029_a(crashreportcategory);
            }

            throw new ReportedException(crashreport);
         }

         if(entity.field_70128_L) {
            this.field_73007_j.remove(i--);
         }
      }

      this.field_72984_F.func_76318_c("remove");
      this.field_72996_f.removeAll(this.field_72997_g);

      for(int k = 0; k < this.field_72997_g.size(); ++k) {
         Entity entity1 = (Entity)this.field_72997_g.get(k);
         int j = entity1.field_70176_ah;
         int l1 = entity1.field_70164_aj;
         if(entity1.field_70175_ag && this.func_175680_a(j, l1, true)) {
            this.func_72964_e(j, l1).func_76622_b(entity1);
         }
      }

      for(int l = 0; l < this.field_72997_g.size(); ++l) {
         this.func_72847_b((Entity)this.field_72997_g.get(l));
      }

      this.field_72997_g.clear();
      this.field_72984_F.func_76318_c("regular");

      for(int i1 = 0; i1 < this.field_72996_f.size(); ++i1) {
         Entity entity2 = (Entity)this.field_72996_f.get(i1);
         if(entity2.field_70154_o != null) {
            if(!entity2.field_70154_o.field_70128_L && entity2.field_70154_o.field_70153_n == entity2) {
               continue;
            }

            entity2.field_70154_o.field_70153_n = null;
            entity2.field_70154_o = null;
         }

         this.field_72984_F.func_76320_a("tick");
         if(!entity2.field_70128_L) {
            try {
               this.func_72870_g(entity2);
            } catch (Throwable throwable1) {
               CrashReport crashreport1 = CrashReport.func_85055_a(throwable1, "Ticking entity");
               CrashReportCategory crashreportcategory2 = crashreport1.func_85058_a("Entity being ticked");
               entity2.func_85029_a(crashreportcategory2);
               throw new ReportedException(crashreport1);
            }
         }

         this.field_72984_F.func_76319_b();
         this.field_72984_F.func_76320_a("remove");
         if(entity2.field_70128_L) {
            int k1 = entity2.field_70176_ah;
            int i2 = entity2.field_70164_aj;
            if(entity2.field_70175_ag && this.func_175680_a(k1, i2, true)) {
               this.func_72964_e(k1, i2).func_76622_b(entity2);
            }

            this.field_72996_f.remove(i1--);
            this.func_72847_b(entity2);
         }

         this.field_72984_F.func_76319_b();
      }

      this.field_72984_F.func_76318_c("blockEntities");
      this.field_147481_N = true;
      Iterator<TileEntity> iterator = this.field_175730_i.iterator();

      while(iterator.hasNext()) {
         TileEntity tileentity = (TileEntity)iterator.next();
         if(!tileentity.func_145837_r() && tileentity.func_145830_o()) {
            BlockPos blockpos = tileentity.func_174877_v();
            if(this.func_175667_e(blockpos) && this.field_175728_M.func_177746_a(blockpos)) {
               try {
                  ((ITickable)tileentity).func_73660_a();
               } catch (Throwable throwable) {
                  CrashReport crashreport2 = CrashReport.func_85055_a(throwable, "Ticking block entity");
                  CrashReportCategory crashreportcategory1 = crashreport2.func_85058_a("Block entity being ticked");
                  tileentity.func_145828_a(crashreportcategory1);
                  throw new ReportedException(crashreport2);
               }
            }
         }

         if(tileentity.func_145837_r()) {
            iterator.remove();
            this.field_147482_g.remove(tileentity);
            if(this.func_175667_e(tileentity.func_174877_v())) {
               this.func_175726_f(tileentity.func_174877_v()).func_177425_e(tileentity.func_174877_v());
            }
         }
      }

      this.field_147481_N = false;
      if(!this.field_147483_b.isEmpty()) {
         this.field_175730_i.removeAll(this.field_147483_b);
         this.field_147482_g.removeAll(this.field_147483_b);
         this.field_147483_b.clear();
      }

      this.field_72984_F.func_76318_c("pendingBlockEntities");
      if(!this.field_147484_a.isEmpty()) {
         for(int j1 = 0; j1 < this.field_147484_a.size(); ++j1) {
            TileEntity tileentity1 = (TileEntity)this.field_147484_a.get(j1);
            if(!tileentity1.func_145837_r()) {
               if(!this.field_147482_g.contains(tileentity1)) {
                  this.func_175700_a(tileentity1);
               }

               if(this.func_175667_e(tileentity1.func_174877_v())) {
                  this.func_175726_f(tileentity1.func_174877_v()).func_177426_a(tileentity1.func_174877_v(), tileentity1);
               }

               this.func_175689_h(tileentity1.func_174877_v());
            }
         }

         this.field_147484_a.clear();
      }

      this.field_72984_F.func_76319_b();
      this.field_72984_F.func_76319_b();
   }

   public boolean func_175700_a(TileEntity p_175700_1_) {
      boolean flag = this.field_147482_g.add(p_175700_1_);
      if(flag && p_175700_1_ instanceof ITickable) {
         this.field_175730_i.add(p_175700_1_);
      }

      return flag;
   }

   public void func_147448_a(Collection<TileEntity> p_147448_1_) {
      if(this.field_147481_N) {
         this.field_147484_a.addAll(p_147448_1_);
      } else {
         for(TileEntity tileentity : p_147448_1_) {
            this.field_147482_g.add(tileentity);
            if(tileentity instanceof ITickable) {
               this.field_175730_i.add(tileentity);
            }
         }
      }

   }

   public void func_72870_g(Entity p_72870_1_) {
      this.func_72866_a(p_72870_1_, true);
   }

   public void func_72866_a(Entity p_72866_1_, boolean p_72866_2_) {
      int i = MathHelper.func_76128_c(p_72866_1_.field_70165_t);
      int j = MathHelper.func_76128_c(p_72866_1_.field_70161_v);
      int k = 32;
      if(!p_72866_2_ || this.func_175663_a(i - k, 0, j - k, i + k, 0, j + k, true)) {
         p_72866_1_.field_70142_S = p_72866_1_.field_70165_t;
         p_72866_1_.field_70137_T = p_72866_1_.field_70163_u;
         p_72866_1_.field_70136_U = p_72866_1_.field_70161_v;
         p_72866_1_.field_70126_B = p_72866_1_.field_70177_z;
         p_72866_1_.field_70127_C = p_72866_1_.field_70125_A;
         if(p_72866_2_ && p_72866_1_.field_70175_ag) {
            ++p_72866_1_.field_70173_aa;
            if(p_72866_1_.field_70154_o != null) {
               p_72866_1_.func_70098_U();
            } else {
               p_72866_1_.func_70071_h_();
            }
         }

         this.field_72984_F.func_76320_a("chunkCheck");
         if(Double.isNaN(p_72866_1_.field_70165_t) || Double.isInfinite(p_72866_1_.field_70165_t)) {
            p_72866_1_.field_70165_t = p_72866_1_.field_70142_S;
         }

         if(Double.isNaN(p_72866_1_.field_70163_u) || Double.isInfinite(p_72866_1_.field_70163_u)) {
            p_72866_1_.field_70163_u = p_72866_1_.field_70137_T;
         }

         if(Double.isNaN(p_72866_1_.field_70161_v) || Double.isInfinite(p_72866_1_.field_70161_v)) {
            p_72866_1_.field_70161_v = p_72866_1_.field_70136_U;
         }

         if(Double.isNaN((double)p_72866_1_.field_70125_A) || Double.isInfinite((double)p_72866_1_.field_70125_A)) {
            p_72866_1_.field_70125_A = p_72866_1_.field_70127_C;
         }

         if(Double.isNaN((double)p_72866_1_.field_70177_z) || Double.isInfinite((double)p_72866_1_.field_70177_z)) {
            p_72866_1_.field_70177_z = p_72866_1_.field_70126_B;
         }

         int l = MathHelper.func_76128_c(p_72866_1_.field_70165_t / 16.0D);
         int i1 = MathHelper.func_76128_c(p_72866_1_.field_70163_u / 16.0D);
         int j1 = MathHelper.func_76128_c(p_72866_1_.field_70161_v / 16.0D);
         if(!p_72866_1_.field_70175_ag || p_72866_1_.field_70176_ah != l || p_72866_1_.field_70162_ai != i1 || p_72866_1_.field_70164_aj != j1) {
            if(p_72866_1_.field_70175_ag && this.func_175680_a(p_72866_1_.field_70176_ah, p_72866_1_.field_70164_aj, true)) {
               this.func_72964_e(p_72866_1_.field_70176_ah, p_72866_1_.field_70164_aj).func_76608_a(p_72866_1_, p_72866_1_.field_70162_ai);
            }

            if(this.func_175680_a(l, j1, true)) {
               p_72866_1_.field_70175_ag = true;
               this.func_72964_e(l, j1).func_76612_a(p_72866_1_);
            } else {
               p_72866_1_.field_70175_ag = false;
            }
         }

         this.field_72984_F.func_76319_b();
         if(p_72866_2_ && p_72866_1_.field_70175_ag && p_72866_1_.field_70153_n != null) {
            if(!p_72866_1_.field_70153_n.field_70128_L && p_72866_1_.field_70153_n.field_70154_o == p_72866_1_) {
               this.func_72870_g(p_72866_1_.field_70153_n);
            } else {
               p_72866_1_.field_70153_n.field_70154_o = null;
               p_72866_1_.field_70153_n = null;
            }
         }

      }
   }

   public boolean func_72855_b(AxisAlignedBB p_72855_1_) {
      return this.func_72917_a(p_72855_1_, (Entity)null);
   }

   public boolean func_72917_a(AxisAlignedBB p_72917_1_, Entity p_72917_2_) {
      List<Entity> list = this.func_72839_b((Entity)null, p_72917_1_);

      for(int i = 0; i < list.size(); ++i) {
         Entity entity = (Entity)list.get(i);
         if(!entity.field_70128_L && entity.field_70156_m && entity != p_72917_2_ && (p_72917_2_ == null || p_72917_2_.field_70154_o != entity && p_72917_2_.field_70153_n != entity)) {
            return false;
         }
      }

      return true;
   }

   public boolean func_72829_c(AxisAlignedBB p_72829_1_) {
      int i = MathHelper.func_76128_c(p_72829_1_.field_72340_a);
      int j = MathHelper.func_76128_c(p_72829_1_.field_72336_d);
      int k = MathHelper.func_76128_c(p_72829_1_.field_72338_b);
      int l = MathHelper.func_76128_c(p_72829_1_.field_72337_e);
      int i1 = MathHelper.func_76128_c(p_72829_1_.field_72339_c);
      int j1 = MathHelper.func_76128_c(p_72829_1_.field_72334_f);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int k1 = i; k1 <= j; ++k1) {
         for(int l1 = k; l1 <= l; ++l1) {
            for(int i2 = i1; i2 <= j1; ++i2) {
               Block block = this.func_180495_p(blockpos$mutableblockpos.func_181079_c(k1, l1, i2)).func_177230_c();
               if(block.func_149688_o() != Material.field_151579_a) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean func_72953_d(AxisAlignedBB p_72953_1_) {
      int i = MathHelper.func_76128_c(p_72953_1_.field_72340_a);
      int j = MathHelper.func_76128_c(p_72953_1_.field_72336_d);
      int k = MathHelper.func_76128_c(p_72953_1_.field_72338_b);
      int l = MathHelper.func_76128_c(p_72953_1_.field_72337_e);
      int i1 = MathHelper.func_76128_c(p_72953_1_.field_72339_c);
      int j1 = MathHelper.func_76128_c(p_72953_1_.field_72334_f);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int k1 = i; k1 <= j; ++k1) {
         for(int l1 = k; l1 <= l; ++l1) {
            for(int i2 = i1; i2 <= j1; ++i2) {
               Block block = this.func_180495_p(blockpos$mutableblockpos.func_181079_c(k1, l1, i2)).func_177230_c();
               if(block.func_149688_o().func_76224_d()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean func_147470_e(AxisAlignedBB p_147470_1_) {
      int i = MathHelper.func_76128_c(p_147470_1_.field_72340_a);
      int j = MathHelper.func_76128_c(p_147470_1_.field_72336_d + 1.0D);
      int k = MathHelper.func_76128_c(p_147470_1_.field_72338_b);
      int l = MathHelper.func_76128_c(p_147470_1_.field_72337_e + 1.0D);
      int i1 = MathHelper.func_76128_c(p_147470_1_.field_72339_c);
      int j1 = MathHelper.func_76128_c(p_147470_1_.field_72334_f + 1.0D);
      if(this.func_175663_a(i, k, i1, j, l, j1, true)) {
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

         for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
               for(int i2 = i1; i2 < j1; ++i2) {
                  Block block = this.func_180495_p(blockpos$mutableblockpos.func_181079_c(k1, l1, i2)).func_177230_c();
                  if(block == Blocks.field_150480_ab || block == Blocks.field_150356_k || block == Blocks.field_150353_l) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean func_72918_a(AxisAlignedBB p_72918_1_, Material p_72918_2_, Entity p_72918_3_) {
      int i = MathHelper.func_76128_c(p_72918_1_.field_72340_a);
      int j = MathHelper.func_76128_c(p_72918_1_.field_72336_d + 1.0D);
      int k = MathHelper.func_76128_c(p_72918_1_.field_72338_b);
      int l = MathHelper.func_76128_c(p_72918_1_.field_72337_e + 1.0D);
      int i1 = MathHelper.func_76128_c(p_72918_1_.field_72339_c);
      int j1 = MathHelper.func_76128_c(p_72918_1_.field_72334_f + 1.0D);
      if(!this.func_175663_a(i, k, i1, j, l, j1, true)) {
         return false;
      } else {
         boolean flag = false;
         Vec3 vec3 = new Vec3(0.0D, 0.0D, 0.0D);
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

         for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
               for(int i2 = i1; i2 < j1; ++i2) {
                  blockpos$mutableblockpos.func_181079_c(k1, l1, i2);
                  IBlockState iblockstate = this.func_180495_p(blockpos$mutableblockpos);
                  Block block = iblockstate.func_177230_c();
                  if(block.func_149688_o() == p_72918_2_) {
                     double d0 = (double)((float)(l1 + 1) - BlockLiquid.func_149801_b(((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue()));
                     if((double)l >= d0) {
                        flag = true;
                        vec3 = block.func_176197_a(this, blockpos$mutableblockpos, p_72918_3_, vec3);
                     }
                  }
               }
            }
         }

         if(vec3.func_72433_c() > 0.0D && p_72918_3_.func_96092_aw()) {
            vec3 = vec3.func_72432_b();
            double d1 = 0.014D;
            p_72918_3_.field_70159_w += vec3.field_72450_a * d1;
            p_72918_3_.field_70181_x += vec3.field_72448_b * d1;
            p_72918_3_.field_70179_y += vec3.field_72449_c * d1;
         }

         return flag;
      }
   }

   public boolean func_72875_a(AxisAlignedBB p_72875_1_, Material p_72875_2_) {
      int i = MathHelper.func_76128_c(p_72875_1_.field_72340_a);
      int j = MathHelper.func_76128_c(p_72875_1_.field_72336_d + 1.0D);
      int k = MathHelper.func_76128_c(p_72875_1_.field_72338_b);
      int l = MathHelper.func_76128_c(p_72875_1_.field_72337_e + 1.0D);
      int i1 = MathHelper.func_76128_c(p_72875_1_.field_72339_c);
      int j1 = MathHelper.func_76128_c(p_72875_1_.field_72334_f + 1.0D);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int k1 = i; k1 < j; ++k1) {
         for(int l1 = k; l1 < l; ++l1) {
            for(int i2 = i1; i2 < j1; ++i2) {
               if(this.func_180495_p(blockpos$mutableblockpos.func_181079_c(k1, l1, i2)).func_177230_c().func_149688_o() == p_72875_2_) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean func_72830_b(AxisAlignedBB p_72830_1_, Material p_72830_2_) {
      int i = MathHelper.func_76128_c(p_72830_1_.field_72340_a);
      int j = MathHelper.func_76128_c(p_72830_1_.field_72336_d + 1.0D);
      int k = MathHelper.func_76128_c(p_72830_1_.field_72338_b);
      int l = MathHelper.func_76128_c(p_72830_1_.field_72337_e + 1.0D);
      int i1 = MathHelper.func_76128_c(p_72830_1_.field_72339_c);
      int j1 = MathHelper.func_76128_c(p_72830_1_.field_72334_f + 1.0D);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int k1 = i; k1 < j; ++k1) {
         for(int l1 = k; l1 < l; ++l1) {
            for(int i2 = i1; i2 < j1; ++i2) {
               IBlockState iblockstate = this.func_180495_p(blockpos$mutableblockpos.func_181079_c(k1, l1, i2));
               Block block = iblockstate.func_177230_c();
               if(block.func_149688_o() == p_72830_2_) {
                  int j2 = ((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue();
                  double d0 = (double)(l1 + 1);
                  if(j2 < 8) {
                     d0 = (double)(l1 + 1) - (double)j2 / 8.0D;
                  }

                  if(d0 >= p_72830_1_.field_72338_b) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public Explosion func_72876_a(Entity p_72876_1_, double p_72876_2_, double p_72876_4_, double p_72876_6_, float p_72876_8_, boolean p_72876_9_) {
      return this.func_72885_a(p_72876_1_, p_72876_2_, p_72876_4_, p_72876_6_, p_72876_8_, false, p_72876_9_);
   }

   public Explosion func_72885_a(Entity p_72885_1_, double p_72885_2_, double p_72885_4_, double p_72885_6_, float p_72885_8_, boolean p_72885_9_, boolean p_72885_10_) {
      Explosion explosion = new Explosion(this, p_72885_1_, p_72885_2_, p_72885_4_, p_72885_6_, p_72885_8_, p_72885_9_, p_72885_10_);
      explosion.func_77278_a();
      explosion.func_77279_a(true);
      return explosion;
   }

   public float func_72842_a(Vec3 p_72842_1_, AxisAlignedBB p_72842_2_) {
      double d0 = 1.0D / ((p_72842_2_.field_72336_d - p_72842_2_.field_72340_a) * 2.0D + 1.0D);
      double d1 = 1.0D / ((p_72842_2_.field_72337_e - p_72842_2_.field_72338_b) * 2.0D + 1.0D);
      double d2 = 1.0D / ((p_72842_2_.field_72334_f - p_72842_2_.field_72339_c) * 2.0D + 1.0D);
      double d3 = (1.0D - Math.floor(1.0D / d0) * d0) / 2.0D;
      double d4 = (1.0D - Math.floor(1.0D / d2) * d2) / 2.0D;
      if(d0 >= 0.0D && d1 >= 0.0D && d2 >= 0.0D) {
         int i = 0;
         int j = 0;

         for(float f = 0.0F; f <= 1.0F; f = (float)((double)f + d0)) {
            for(float f1 = 0.0F; f1 <= 1.0F; f1 = (float)((double)f1 + d1)) {
               for(float f2 = 0.0F; f2 <= 1.0F; f2 = (float)((double)f2 + d2)) {
                  double d5 = p_72842_2_.field_72340_a + (p_72842_2_.field_72336_d - p_72842_2_.field_72340_a) * (double)f;
                  double d6 = p_72842_2_.field_72338_b + (p_72842_2_.field_72337_e - p_72842_2_.field_72338_b) * (double)f1;
                  double d7 = p_72842_2_.field_72339_c + (p_72842_2_.field_72334_f - p_72842_2_.field_72339_c) * (double)f2;
                  if(this.func_72933_a(new Vec3(d5 + d3, d6, d7 + d4), p_72842_1_) == null) {
                     ++i;
                  }

                  ++j;
               }
            }
         }

         return (float)i / (float)j;
      } else {
         return 0.0F;
      }
   }

   public boolean func_175719_a(EntityPlayer p_175719_1_, BlockPos p_175719_2_, EnumFacing p_175719_3_) {
      p_175719_2_ = p_175719_2_.func_177972_a(p_175719_3_);
      if(this.func_180495_p(p_175719_2_).func_177230_c() == Blocks.field_150480_ab) {
         this.func_180498_a(p_175719_1_, 1004, p_175719_2_, 0);
         this.func_175698_g(p_175719_2_);
         return true;
      } else {
         return false;
      }
   }

   public String func_72981_t() {
      return "All: " + this.field_72996_f.size();
   }

   public String func_72827_u() {
      return this.field_73020_y.func_73148_d();
   }

   public TileEntity func_175625_s(BlockPos p_175625_1_) {
      if(!this.func_175701_a(p_175625_1_)) {
         return null;
      } else {
         TileEntity tileentity = null;
         if(this.field_147481_N) {
            for(int i = 0; i < this.field_147484_a.size(); ++i) {
               TileEntity tileentity1 = (TileEntity)this.field_147484_a.get(i);
               if(!tileentity1.func_145837_r() && tileentity1.func_174877_v().equals(p_175625_1_)) {
                  tileentity = tileentity1;
                  break;
               }
            }
         }

         if(tileentity == null) {
            tileentity = this.func_175726_f(p_175625_1_).func_177424_a(p_175625_1_, Chunk.EnumCreateEntityType.IMMEDIATE);
         }

         if(tileentity == null) {
            for(int j = 0; j < this.field_147484_a.size(); ++j) {
               TileEntity tileentity2 = (TileEntity)this.field_147484_a.get(j);
               if(!tileentity2.func_145837_r() && tileentity2.func_174877_v().equals(p_175625_1_)) {
                  tileentity = tileentity2;
                  break;
               }
            }
         }

         return tileentity;
      }
   }

   public void func_175690_a(BlockPos p_175690_1_, TileEntity p_175690_2_) {
      if(p_175690_2_ != null && !p_175690_2_.func_145837_r()) {
         if(this.field_147481_N) {
            p_175690_2_.func_174878_a(p_175690_1_);
            Iterator<TileEntity> iterator = this.field_147484_a.iterator();

            while(iterator.hasNext()) {
               TileEntity tileentity = (TileEntity)iterator.next();
               if(tileentity.func_174877_v().equals(p_175690_1_)) {
                  tileentity.func_145843_s();
                  iterator.remove();
               }
            }

            this.field_147484_a.add(p_175690_2_);
         } else {
            this.func_175700_a(p_175690_2_);
            this.func_175726_f(p_175690_1_).func_177426_a(p_175690_1_, p_175690_2_);
         }
      }

   }

   public void func_175713_t(BlockPos p_175713_1_) {
      TileEntity tileentity = this.func_175625_s(p_175713_1_);
      if(tileentity != null && this.field_147481_N) {
         tileentity.func_145843_s();
         this.field_147484_a.remove(tileentity);
      } else {
         if(tileentity != null) {
            this.field_147484_a.remove(tileentity);
            this.field_147482_g.remove(tileentity);
            this.field_175730_i.remove(tileentity);
         }

         this.func_175726_f(p_175713_1_).func_177425_e(p_175713_1_);
      }

   }

   public void func_147457_a(TileEntity p_147457_1_) {
      this.field_147483_b.add(p_147457_1_);
   }

   public boolean func_175665_u(BlockPos p_175665_1_) {
      IBlockState iblockstate = this.func_180495_p(p_175665_1_);
      AxisAlignedBB axisalignedbb = iblockstate.func_177230_c().func_180640_a(this, p_175665_1_, iblockstate);
      return axisalignedbb != null && axisalignedbb.func_72320_b() >= 1.0D;
   }

   public static boolean func_175683_a(IBlockAccess p_175683_0_, BlockPos p_175683_1_) {
      IBlockState iblockstate = p_175683_0_.func_180495_p(p_175683_1_);
      Block block = iblockstate.func_177230_c();
      return block.func_149688_o().func_76218_k() && block.func_149686_d()?true:(block instanceof BlockStairs?iblockstate.func_177229_b(BlockStairs.field_176308_b) == BlockStairs.EnumHalf.TOP:(block instanceof BlockSlab?iblockstate.func_177229_b(BlockSlab.field_176554_a) == BlockSlab.EnumBlockHalf.TOP:(block instanceof BlockHopper?true:(block instanceof BlockSnow?((Integer)iblockstate.func_177229_b(BlockSnow.field_176315_a)).intValue() == 7:false))));
   }

   public boolean func_175677_d(BlockPos p_175677_1_, boolean p_175677_2_) {
      if(!this.func_175701_a(p_175677_1_)) {
         return p_175677_2_;
      } else {
         Chunk chunk = this.field_73020_y.func_177459_a(p_175677_1_);
         if(chunk.func_76621_g()) {
            return p_175677_2_;
         } else {
            Block block = this.func_180495_p(p_175677_1_).func_177230_c();
            return block.func_149688_o().func_76218_k() && block.func_149686_d();
         }
      }
   }

   public void func_72966_v() {
      int i = this.func_72967_a(1.0F);
      if(i != this.field_73008_k) {
         this.field_73008_k = i;
      }

   }

   public void func_72891_a(boolean p_72891_1_, boolean p_72891_2_) {
      this.field_72985_G = p_72891_1_;
      this.field_72992_H = p_72891_2_;
   }

   public void func_72835_b() {
      this.func_72979_l();
   }

   protected void func_72947_a() {
      if(this.field_72986_A.func_76059_o()) {
         this.field_73004_o = 1.0F;
         if(this.field_72986_A.func_76061_m()) {
            this.field_73017_q = 1.0F;
         }
      }

   }

   protected void func_72979_l() {
      if(!this.field_73011_w.func_177495_o()) {
         if(!this.field_72995_K) {
            int i = this.field_72986_A.func_176133_A();
            if(i > 0) {
               --i;
               this.field_72986_A.func_176142_i(i);
               this.field_72986_A.func_76090_f(this.field_72986_A.func_76061_m()?1:2);
               this.field_72986_A.func_76080_g(this.field_72986_A.func_76059_o()?1:2);
            }

            int j = this.field_72986_A.func_76071_n();
            if(j <= 0) {
               if(this.field_72986_A.func_76061_m()) {
                  this.field_72986_A.func_76090_f(this.field_73012_v.nextInt(12000) + 3600);
               } else {
                  this.field_72986_A.func_76090_f(this.field_73012_v.nextInt(168000) + 12000);
               }
            } else {
               --j;
               this.field_72986_A.func_76090_f(j);
               if(j <= 0) {
                  this.field_72986_A.func_76069_a(!this.field_72986_A.func_76061_m());
               }
            }

            this.field_73018_p = this.field_73017_q;
            if(this.field_72986_A.func_76061_m()) {
               this.field_73017_q = (float)((double)this.field_73017_q + 0.01D);
            } else {
               this.field_73017_q = (float)((double)this.field_73017_q - 0.01D);
            }

            this.field_73017_q = MathHelper.func_76131_a(this.field_73017_q, 0.0F, 1.0F);
            int k = this.field_72986_A.func_76083_p();
            if(k <= 0) {
               if(this.field_72986_A.func_76059_o()) {
                  this.field_72986_A.func_76080_g(this.field_73012_v.nextInt(12000) + 12000);
               } else {
                  this.field_72986_A.func_76080_g(this.field_73012_v.nextInt(168000) + 12000);
               }
            } else {
               --k;
               this.field_72986_A.func_76080_g(k);
               if(k <= 0) {
                  this.field_72986_A.func_76084_b(!this.field_72986_A.func_76059_o());
               }
            }

            this.field_73003_n = this.field_73004_o;
            if(this.field_72986_A.func_76059_o()) {
               this.field_73004_o = (float)((double)this.field_73004_o + 0.01D);
            } else {
               this.field_73004_o = (float)((double)this.field_73004_o - 0.01D);
            }

            this.field_73004_o = MathHelper.func_76131_a(this.field_73004_o, 0.0F, 1.0F);
         }
      }
   }

   protected void func_72903_x() {
      this.field_72993_I.clear();
      this.field_72984_F.func_76320_a("buildList");

      for(int i = 0; i < this.field_73010_i.size(); ++i) {
         EntityPlayer entityplayer = (EntityPlayer)this.field_73010_i.get(i);
         int j = MathHelper.func_76128_c(entityplayer.field_70165_t / 16.0D);
         int k = MathHelper.func_76128_c(entityplayer.field_70161_v / 16.0D);
         int l = this.func_152379_p();

         for(int i1 = -l; i1 <= l; ++i1) {
            for(int j1 = -l; j1 <= l; ++j1) {
               this.field_72993_I.add(new ChunkCoordIntPair(i1 + j, j1 + k));
            }
         }
      }

      this.field_72984_F.func_76319_b();
      if(this.field_72990_M > 0) {
         --this.field_72990_M;
      }

      this.field_72984_F.func_76320_a("playerCheckLight");
      if(!this.field_73010_i.isEmpty()) {
         int k1 = this.field_73012_v.nextInt(this.field_73010_i.size());
         EntityPlayer entityplayer1 = (EntityPlayer)this.field_73010_i.get(k1);
         int l1 = MathHelper.func_76128_c(entityplayer1.field_70165_t) + this.field_73012_v.nextInt(11) - 5;
         int i2 = MathHelper.func_76128_c(entityplayer1.field_70163_u) + this.field_73012_v.nextInt(11) - 5;
         int j2 = MathHelper.func_76128_c(entityplayer1.field_70161_v) + this.field_73012_v.nextInt(11) - 5;
         this.func_175664_x(new BlockPos(l1, i2, j2));
      }

      this.field_72984_F.func_76319_b();
   }

   protected abstract int func_152379_p();

   protected void func_147467_a(int p_147467_1_, int p_147467_2_, Chunk p_147467_3_) {
      this.field_72984_F.func_76318_c("moodSound");
      if(this.field_72990_M == 0 && !this.field_72995_K) {
         this.field_73005_l = this.field_73005_l * 3 + 1013904223;
         int i = this.field_73005_l >> 2;
         int j = i & 15;
         int k = i >> 8 & 15;
         int l = i >> 16 & 255;
         BlockPos blockpos = new BlockPos(j, l, k);
         Block block = p_147467_3_.func_177428_a(blockpos);
         j = j + p_147467_1_;
         k = k + p_147467_2_;
         if(block.func_149688_o() == Material.field_151579_a && this.func_175699_k(blockpos) <= this.field_73012_v.nextInt(8) && this.func_175642_b(EnumSkyBlock.SKY, blockpos) <= 0) {
            EntityPlayer entityplayer = this.func_72977_a((double)j + 0.5D, (double)l + 0.5D, (double)k + 0.5D, 8.0D);
            if(entityplayer != null && entityplayer.func_70092_e((double)j + 0.5D, (double)l + 0.5D, (double)k + 0.5D) > 4.0D) {
               this.func_72908_a((double)j + 0.5D, (double)l + 0.5D, (double)k + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.field_73012_v.nextFloat() * 0.2F);
               this.field_72990_M = this.field_73012_v.nextInt(12000) + 6000;
            }
         }
      }

      this.field_72984_F.func_76318_c("checkLight");
      p_147467_3_.func_76594_o();
   }

   protected void func_147456_g() {
      this.func_72903_x();
   }

   public void func_175637_a(Block p_175637_1_, BlockPos p_175637_2_, Random p_175637_3_) {
      this.field_72999_e = true;
      p_175637_1_.func_180650_b(this, p_175637_2_, this.func_180495_p(p_175637_2_), p_175637_3_);
      this.field_72999_e = false;
   }

   public boolean func_175675_v(BlockPos p_175675_1_) {
      return this.func_175670_e(p_175675_1_, false);
   }

   public boolean func_175662_w(BlockPos p_175662_1_) {
      return this.func_175670_e(p_175662_1_, true);
   }

   public boolean func_175670_e(BlockPos p_175670_1_, boolean p_175670_2_) {
      BiomeGenBase biomegenbase = this.func_180494_b(p_175670_1_);
      float f = biomegenbase.func_180626_a(p_175670_1_);
      if(f > 0.15F) {
         return false;
      } else {
         if(p_175670_1_.func_177956_o() >= 0 && p_175670_1_.func_177956_o() < 256 && this.func_175642_b(EnumSkyBlock.BLOCK, p_175670_1_) < 10) {
            IBlockState iblockstate = this.func_180495_p(p_175670_1_);
            Block block = iblockstate.func_177230_c();
            if((block == Blocks.field_150355_j || block == Blocks.field_150358_i) && ((Integer)iblockstate.func_177229_b(BlockLiquid.field_176367_b)).intValue() == 0) {
               if(!p_175670_2_) {
                  return true;
               }

               boolean flag = this.func_175696_F(p_175670_1_.func_177976_e()) && this.func_175696_F(p_175670_1_.func_177974_f()) && this.func_175696_F(p_175670_1_.func_177978_c()) && this.func_175696_F(p_175670_1_.func_177968_d());
               if(!flag) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private boolean func_175696_F(BlockPos p_175696_1_) {
      return this.func_180495_p(p_175696_1_).func_177230_c().func_149688_o() == Material.field_151586_h;
   }

   public boolean func_175708_f(BlockPos p_175708_1_, boolean p_175708_2_) {
      BiomeGenBase biomegenbase = this.func_180494_b(p_175708_1_);
      float f = biomegenbase.func_180626_a(p_175708_1_);
      if(f > 0.15F) {
         return false;
      } else if(!p_175708_2_) {
         return true;
      } else {
         if(p_175708_1_.func_177956_o() >= 0 && p_175708_1_.func_177956_o() < 256 && this.func_175642_b(EnumSkyBlock.BLOCK, p_175708_1_) < 10) {
            Block block = this.func_180495_p(p_175708_1_).func_177230_c();
            if(block.func_149688_o() == Material.field_151579_a && Blocks.field_150431_aC.func_176196_c(this, p_175708_1_)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean func_175664_x(BlockPos p_175664_1_) {
      boolean flag = false;
      if(!this.field_73011_w.func_177495_o()) {
         flag |= this.func_180500_c(EnumSkyBlock.SKY, p_175664_1_);
      }

      flag = flag | this.func_180500_c(EnumSkyBlock.BLOCK, p_175664_1_);
      return flag;
   }

   private int func_175638_a(BlockPos p_175638_1_, EnumSkyBlock p_175638_2_) {
      if(p_175638_2_ == EnumSkyBlock.SKY && this.func_175678_i(p_175638_1_)) {
         return 15;
      } else {
         Block block = this.func_180495_p(p_175638_1_).func_177230_c();
         int i = p_175638_2_ == EnumSkyBlock.SKY?0:block.func_149750_m();
         int j = block.func_149717_k();
         if(j >= 15 && block.func_149750_m() > 0) {
            j = 1;
         }

         if(j < 1) {
            j = 1;
         }

         if(j >= 15) {
            return 0;
         } else if(i >= 14) {
            return i;
         } else {
            for(EnumFacing enumfacing : EnumFacing.values()) {
               BlockPos blockpos = p_175638_1_.func_177972_a(enumfacing);
               int k = this.func_175642_b(p_175638_2_, blockpos) - j;
               if(k > i) {
                  i = k;
               }

               if(i >= 14) {
                  return i;
               }
            }

            return i;
         }
      }
   }

   public boolean func_180500_c(EnumSkyBlock p_180500_1_, BlockPos p_180500_2_) {
      if(!this.func_175648_a(p_180500_2_, 17, false)) {
         return false;
      } else {
         int i = 0;
         int j = 0;
         this.field_72984_F.func_76320_a("getBrightness");
         int k = this.func_175642_b(p_180500_1_, p_180500_2_);
         int l = this.func_175638_a(p_180500_2_, p_180500_1_);
         int i1 = p_180500_2_.func_177958_n();
         int j1 = p_180500_2_.func_177956_o();
         int k1 = p_180500_2_.func_177952_p();
         if(l > k) {
            this.field_72994_J[j++] = 133152;
         } else if(l < k) {
            this.field_72994_J[j++] = 133152 | k << 18;

            while(i < j) {
               int l1 = this.field_72994_J[i++];
               int i2 = (l1 & 63) - 32 + i1;
               int j2 = (l1 >> 6 & 63) - 32 + j1;
               int k2 = (l1 >> 12 & 63) - 32 + k1;
               int l2 = l1 >> 18 & 15;
               BlockPos blockpos = new BlockPos(i2, j2, k2);
               int i3 = this.func_175642_b(p_180500_1_, blockpos);
               if(i3 == l2) {
                  this.func_175653_a(p_180500_1_, blockpos, 0);
                  if(l2 > 0) {
                     int j3 = MathHelper.func_76130_a(i2 - i1);
                     int k3 = MathHelper.func_76130_a(j2 - j1);
                     int l3 = MathHelper.func_76130_a(k2 - k1);
                     if(j3 + k3 + l3 < 17) {
                        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                        for(EnumFacing enumfacing : EnumFacing.values()) {
                           int i4 = i2 + enumfacing.func_82601_c();
                           int j4 = j2 + enumfacing.func_96559_d();
                           int k4 = k2 + enumfacing.func_82599_e();
                           blockpos$mutableblockpos.func_181079_c(i4, j4, k4);
                           int l4 = Math.max(1, this.func_180495_p(blockpos$mutableblockpos).func_177230_c().func_149717_k());
                           i3 = this.func_175642_b(p_180500_1_, blockpos$mutableblockpos);
                           if(i3 == l2 - l4 && j < this.field_72994_J.length) {
                              this.field_72994_J[j++] = i4 - i1 + 32 | j4 - j1 + 32 << 6 | k4 - k1 + 32 << 12 | l2 - l4 << 18;
                           }
                        }
                     }
                  }
               }
            }

            i = 0;
         }

         this.field_72984_F.func_76319_b();
         this.field_72984_F.func_76320_a("checkedPosition < toCheckCount");

         while(i < j) {
            int i5 = this.field_72994_J[i++];
            int j5 = (i5 & 63) - 32 + i1;
            int k5 = (i5 >> 6 & 63) - 32 + j1;
            int l5 = (i5 >> 12 & 63) - 32 + k1;
            BlockPos blockpos1 = new BlockPos(j5, k5, l5);
            int i6 = this.func_175642_b(p_180500_1_, blockpos1);
            int j6 = this.func_175638_a(blockpos1, p_180500_1_);
            if(j6 != i6) {
               this.func_175653_a(p_180500_1_, blockpos1, j6);
               if(j6 > i6) {
                  int k6 = Math.abs(j5 - i1);
                  int l6 = Math.abs(k5 - j1);
                  int i7 = Math.abs(l5 - k1);
                  boolean flag = j < this.field_72994_J.length - 6;
                  if(k6 + l6 + i7 < 17 && flag) {
                     if(this.func_175642_b(p_180500_1_, blockpos1.func_177976_e()) < j6) {
                        this.field_72994_J[j++] = j5 - 1 - i1 + 32 + (k5 - j1 + 32 << 6) + (l5 - k1 + 32 << 12);
                     }

                     if(this.func_175642_b(p_180500_1_, blockpos1.func_177974_f()) < j6) {
                        this.field_72994_J[j++] = j5 + 1 - i1 + 32 + (k5 - j1 + 32 << 6) + (l5 - k1 + 32 << 12);
                     }

                     if(this.func_175642_b(p_180500_1_, blockpos1.func_177977_b()) < j6) {
                        this.field_72994_J[j++] = j5 - i1 + 32 + (k5 - 1 - j1 + 32 << 6) + (l5 - k1 + 32 << 12);
                     }

                     if(this.func_175642_b(p_180500_1_, blockpos1.func_177984_a()) < j6) {
                        this.field_72994_J[j++] = j5 - i1 + 32 + (k5 + 1 - j1 + 32 << 6) + (l5 - k1 + 32 << 12);
                     }

                     if(this.func_175642_b(p_180500_1_, blockpos1.func_177978_c()) < j6) {
                        this.field_72994_J[j++] = j5 - i1 + 32 + (k5 - j1 + 32 << 6) + (l5 - 1 - k1 + 32 << 12);
                     }

                     if(this.func_175642_b(p_180500_1_, blockpos1.func_177968_d()) < j6) {
                        this.field_72994_J[j++] = j5 - i1 + 32 + (k5 - j1 + 32 << 6) + (l5 + 1 - k1 + 32 << 12);
                     }
                  }
               }
            }
         }

         this.field_72984_F.func_76319_b();
         return true;
      }
   }

   public boolean func_72955_a(boolean p_72955_1_) {
      return false;
   }

   public List<NextTickListEntry> func_72920_a(Chunk p_72920_1_, boolean p_72920_2_) {
      return null;
   }

   public List<NextTickListEntry> func_175712_a(StructureBoundingBox p_175712_1_, boolean p_175712_2_) {
      return null;
   }

   public List<Entity> func_72839_b(Entity p_72839_1_, AxisAlignedBB p_72839_2_) {
      return this.func_175674_a(p_72839_1_, p_72839_2_, EntitySelectors.field_180132_d);
   }

   public List<Entity> func_175674_a(Entity p_175674_1_, AxisAlignedBB p_175674_2_, Predicate<? super Entity> p_175674_3_) {
      List<Entity> list = Lists.<Entity>newArrayList();
      int i = MathHelper.func_76128_c((p_175674_2_.field_72340_a - 2.0D) / 16.0D);
      int j = MathHelper.func_76128_c((p_175674_2_.field_72336_d + 2.0D) / 16.0D);
      int k = MathHelper.func_76128_c((p_175674_2_.field_72339_c - 2.0D) / 16.0D);
      int l = MathHelper.func_76128_c((p_175674_2_.field_72334_f + 2.0D) / 16.0D);

      for(int i1 = i; i1 <= j; ++i1) {
         for(int j1 = k; j1 <= l; ++j1) {
            if(this.func_175680_a(i1, j1, true)) {
               this.func_72964_e(i1, j1).func_177414_a(p_175674_1_, p_175674_2_, list, p_175674_3_);
            }
         }
      }

      return list;
   }

   public <T extends Entity> List<T> func_175644_a(Class<? extends T> p_175644_1_, Predicate<? super T> p_175644_2_) {
      List<T> list = Lists.<T>newArrayList();

      for(Entity entity : this.field_72996_f) {
         if(p_175644_1_.isAssignableFrom(entity.getClass()) && p_175644_2_.apply(entity)) {
            list.add(entity);
         }
      }

      return list;
   }

   public <T extends Entity> List<T> func_175661_b(Class<? extends T> p_175661_1_, Predicate<? super T> p_175661_2_) {
      List<T> list = Lists.<T>newArrayList();

      for(Entity entity : this.field_73010_i) {
         if(p_175661_1_.isAssignableFrom(entity.getClass()) && p_175661_2_.apply(entity)) {
            list.add(entity);
         }
      }

      return list;
   }

   public <T extends Entity> List<T> func_72872_a(Class<? extends T> p_72872_1_, AxisAlignedBB p_72872_2_) {
      return this.<T>func_175647_a(p_72872_1_, p_72872_2_, EntitySelectors.field_180132_d);
   }

   public <T extends Entity> List<T> func_175647_a(Class<? extends T> p_175647_1_, AxisAlignedBB p_175647_2_, Predicate<? super T> p_175647_3_) {
      int i = MathHelper.func_76128_c((p_175647_2_.field_72340_a - 2.0D) / 16.0D);
      int j = MathHelper.func_76128_c((p_175647_2_.field_72336_d + 2.0D) / 16.0D);
      int k = MathHelper.func_76128_c((p_175647_2_.field_72339_c - 2.0D) / 16.0D);
      int l = MathHelper.func_76128_c((p_175647_2_.field_72334_f + 2.0D) / 16.0D);
      List<T> list = Lists.<T>newArrayList();

      for(int i1 = i; i1 <= j; ++i1) {
         for(int j1 = k; j1 <= l; ++j1) {
            if(this.func_175680_a(i1, j1, true)) {
               this.func_72964_e(i1, j1).func_177430_a(p_175647_1_, p_175647_2_, list, p_175647_3_);
            }
         }
      }

      return list;
   }

   public <T extends Entity> T func_72857_a(Class<? extends T> p_72857_1_, AxisAlignedBB p_72857_2_, T p_72857_3_) {
      List<T> list = this.<T>func_72872_a(p_72857_1_, p_72857_2_);
      T t = null;
      double d0 = Double.MAX_VALUE;

      for(int i = 0; i < list.size(); ++i) {
         T t1 = (Entity)list.get(i);
         if(t1 != p_72857_3_ && EntitySelectors.field_180132_d.apply(t1)) {
            double d1 = p_72857_3_.func_70068_e(t1);
            if(d1 <= d0) {
               t = t1;
               d0 = d1;
            }
         }
      }

      return t;
   }

   public Entity func_73045_a(int p_73045_1_) {
      return (Entity)this.field_175729_l.func_76041_a(p_73045_1_);
   }

   public List<Entity> func_72910_y() {
      return this.field_72996_f;
   }

   public void func_175646_b(BlockPos p_175646_1_, TileEntity p_175646_2_) {
      if(this.func_175667_e(p_175646_1_)) {
         this.func_175726_f(p_175646_1_).func_76630_e();
      }

   }

   public int func_72907_a(Class<?> p_72907_1_) {
      int i = 0;

      for(Entity entity : this.field_72996_f) {
         if((!(entity instanceof EntityLiving) || !((EntityLiving)entity).func_104002_bU()) && p_72907_1_.isAssignableFrom(entity.getClass())) {
            ++i;
         }
      }

      return i;
   }

   public void func_175650_b(Collection<Entity> p_175650_1_) {
      this.field_72996_f.addAll(p_175650_1_);

      for(Entity entity : p_175650_1_) {
         this.func_72923_a(entity);
      }

   }

   public void func_175681_c(Collection<Entity> p_175681_1_) {
      this.field_72997_g.addAll(p_175681_1_);
   }

   public boolean func_175716_a(Block p_175716_1_, BlockPos p_175716_2_, boolean p_175716_3_, EnumFacing p_175716_4_, Entity p_175716_5_, ItemStack p_175716_6_) {
      Block block = this.func_180495_p(p_175716_2_).func_177230_c();
      AxisAlignedBB axisalignedbb = p_175716_3_?null:p_175716_1_.func_180640_a(this, p_175716_2_, p_175716_1_.func_176223_P());
      return axisalignedbb != null && !this.func_72917_a(axisalignedbb, p_175716_5_)?false:(block.func_149688_o() == Material.field_151594_q && p_175716_1_ == Blocks.field_150467_bQ?true:block.func_149688_o().func_76222_j() && p_175716_1_.func_176193_a(this, p_175716_2_, p_175716_4_, p_175716_6_));
   }

   public int func_181545_F() {
      return this.field_181546_a;
   }

   public void func_181544_b(int p_181544_1_) {
      this.field_181546_a = p_181544_1_;
   }

   public int func_175627_a(BlockPos p_175627_1_, EnumFacing p_175627_2_) {
      IBlockState iblockstate = this.func_180495_p(p_175627_1_);
      return iblockstate.func_177230_c().func_176211_b(this, p_175627_1_, iblockstate, p_175627_2_);
   }

   public WorldType func_175624_G() {
      return this.field_72986_A.func_76067_t();
   }

   public int func_175676_y(BlockPos p_175676_1_) {
      int i = 0;
      i = Math.max(i, this.func_175627_a(p_175676_1_.func_177977_b(), EnumFacing.DOWN));
      if(i >= 15) {
         return i;
      } else {
         i = Math.max(i, this.func_175627_a(p_175676_1_.func_177984_a(), EnumFacing.UP));
         if(i >= 15) {
            return i;
         } else {
            i = Math.max(i, this.func_175627_a(p_175676_1_.func_177978_c(), EnumFacing.NORTH));
            if(i >= 15) {
               return i;
            } else {
               i = Math.max(i, this.func_175627_a(p_175676_1_.func_177968_d(), EnumFacing.SOUTH));
               if(i >= 15) {
                  return i;
               } else {
                  i = Math.max(i, this.func_175627_a(p_175676_1_.func_177976_e(), EnumFacing.WEST));
                  if(i >= 15) {
                     return i;
                  } else {
                     i = Math.max(i, this.func_175627_a(p_175676_1_.func_177974_f(), EnumFacing.EAST));
                     return i >= 15?i:i;
                  }
               }
            }
         }
      }
   }

   public boolean func_175709_b(BlockPos p_175709_1_, EnumFacing p_175709_2_) {
      return this.func_175651_c(p_175709_1_, p_175709_2_) > 0;
   }

   public int func_175651_c(BlockPos p_175651_1_, EnumFacing p_175651_2_) {
      IBlockState iblockstate = this.func_180495_p(p_175651_1_);
      Block block = iblockstate.func_177230_c();
      return block.func_149721_r()?this.func_175676_y(p_175651_1_):block.func_180656_a(this, p_175651_1_, iblockstate, p_175651_2_);
   }

   public boolean func_175640_z(BlockPos p_175640_1_) {
      return this.func_175651_c(p_175640_1_.func_177977_b(), EnumFacing.DOWN) > 0?true:(this.func_175651_c(p_175640_1_.func_177984_a(), EnumFacing.UP) > 0?true:(this.func_175651_c(p_175640_1_.func_177978_c(), EnumFacing.NORTH) > 0?true:(this.func_175651_c(p_175640_1_.func_177968_d(), EnumFacing.SOUTH) > 0?true:(this.func_175651_c(p_175640_1_.func_177976_e(), EnumFacing.WEST) > 0?true:this.func_175651_c(p_175640_1_.func_177974_f(), EnumFacing.EAST) > 0))));
   }

   public int func_175687_A(BlockPos p_175687_1_) {
      int i = 0;

      for(EnumFacing enumfacing : EnumFacing.values()) {
         int j = this.func_175651_c(p_175687_1_.func_177972_a(enumfacing), enumfacing);
         if(j >= 15) {
            return 15;
         }

         if(j > i) {
            i = j;
         }
      }

      return i;
   }

   public EntityPlayer func_72890_a(Entity p_72890_1_, double p_72890_2_) {
      return this.func_72977_a(p_72890_1_.field_70165_t, p_72890_1_.field_70163_u, p_72890_1_.field_70161_v, p_72890_2_);
   }

   public EntityPlayer func_72977_a(double p_72977_1_, double p_72977_3_, double p_72977_5_, double p_72977_7_) {
      double d0 = -1.0D;
      EntityPlayer entityplayer = null;

      for(int i = 0; i < this.field_73010_i.size(); ++i) {
         EntityPlayer entityplayer1 = (EntityPlayer)this.field_73010_i.get(i);
         if(EntitySelectors.field_180132_d.apply(entityplayer1)) {
            double d1 = entityplayer1.func_70092_e(p_72977_1_, p_72977_3_, p_72977_5_);
            if((p_72977_7_ < 0.0D || d1 < p_72977_7_ * p_72977_7_) && (d0 == -1.0D || d1 < d0)) {
               d0 = d1;
               entityplayer = entityplayer1;
            }
         }
      }

      return entityplayer;
   }

   public boolean func_175636_b(double p_175636_1_, double p_175636_3_, double p_175636_5_, double p_175636_7_) {
      for(int i = 0; i < this.field_73010_i.size(); ++i) {
         EntityPlayer entityplayer = (EntityPlayer)this.field_73010_i.get(i);
         if(EntitySelectors.field_180132_d.apply(entityplayer)) {
            double d0 = entityplayer.func_70092_e(p_175636_1_, p_175636_3_, p_175636_5_);
            if(p_175636_7_ < 0.0D || d0 < p_175636_7_ * p_175636_7_) {
               return true;
            }
         }
      }

      return false;
   }

   public EntityPlayer func_72924_a(String p_72924_1_) {
      for(int i = 0; i < this.field_73010_i.size(); ++i) {
         EntityPlayer entityplayer = (EntityPlayer)this.field_73010_i.get(i);
         if(p_72924_1_.equals(entityplayer.func_70005_c_())) {
            return entityplayer;
         }
      }

      return null;
   }

   public EntityPlayer func_152378_a(UUID p_152378_1_) {
      for(int i = 0; i < this.field_73010_i.size(); ++i) {
         EntityPlayer entityplayer = (EntityPlayer)this.field_73010_i.get(i);
         if(p_152378_1_.equals(entityplayer.func_110124_au())) {
            return entityplayer;
         }
      }

      return null;
   }

   public void func_72882_A() {
   }

   public void func_72906_B() throws MinecraftException {
      this.field_73019_z.func_75762_c();
   }

   public void func_82738_a(long p_82738_1_) {
      this.field_72986_A.func_82572_b(p_82738_1_);
   }

   public long func_72905_C() {
      return this.field_72986_A.func_76063_b();
   }

   public long func_82737_E() {
      return this.field_72986_A.func_82573_f();
   }

   public long func_72820_D() {
      return this.field_72986_A.func_76073_f();
   }

   public void func_72877_b(long p_72877_1_) {
      this.field_72986_A.func_76068_b(p_72877_1_);
   }

   public BlockPos func_175694_M() {
      BlockPos blockpos = new BlockPos(this.field_72986_A.func_76079_c(), this.field_72986_A.func_76075_d(), this.field_72986_A.func_76074_e());
      if(!this.func_175723_af().func_177746_a(blockpos)) {
         blockpos = this.func_175645_m(new BlockPos(this.func_175723_af().func_177731_f(), 0.0D, this.func_175723_af().func_177721_g()));
      }

      return blockpos;
   }

   public void func_175652_B(BlockPos p_175652_1_) {
      this.field_72986_A.func_176143_a(p_175652_1_);
   }

   public void func_72897_h(Entity p_72897_1_) {
      int i = MathHelper.func_76128_c(p_72897_1_.field_70165_t / 16.0D);
      int j = MathHelper.func_76128_c(p_72897_1_.field_70161_v / 16.0D);
      int k = 2;

      for(int l = i - k; l <= i + k; ++l) {
         for(int i1 = j - k; i1 <= j + k; ++i1) {
            this.func_72964_e(l, i1);
         }
      }

      if(!this.field_72996_f.contains(p_72897_1_)) {
         this.field_72996_f.add(p_72897_1_);
      }

   }

   public boolean func_175660_a(EntityPlayer p_175660_1_, BlockPos p_175660_2_) {
      return true;
   }

   public void func_72960_a(Entity p_72960_1_, byte p_72960_2_) {
   }

   public IChunkProvider func_72863_F() {
      return this.field_73020_y;
   }

   public void func_175641_c(BlockPos p_175641_1_, Block p_175641_2_, int p_175641_3_, int p_175641_4_) {
      p_175641_2_.func_180648_a(this, p_175641_1_, this.func_180495_p(p_175641_1_), p_175641_3_, p_175641_4_);
   }

   public ISaveHandler func_72860_G() {
      return this.field_73019_z;
   }

   public WorldInfo func_72912_H() {
      return this.field_72986_A;
   }

   public GameRules func_82736_K() {
      return this.field_72986_A.func_82574_x();
   }

   public void func_72854_c() {
   }

   public float func_72819_i(float p_72819_1_) {
      return (this.field_73018_p + (this.field_73017_q - this.field_73018_p) * p_72819_1_) * this.func_72867_j(p_72819_1_);
   }

   public void func_147442_i(float p_147442_1_) {
      this.field_73018_p = p_147442_1_;
      this.field_73017_q = p_147442_1_;
   }

   public float func_72867_j(float p_72867_1_) {
      return this.field_73003_n + (this.field_73004_o - this.field_73003_n) * p_72867_1_;
   }

   public void func_72894_k(float p_72894_1_) {
      this.field_73003_n = p_72894_1_;
      this.field_73004_o = p_72894_1_;
   }

   public boolean func_72911_I() {
      return (double)this.func_72819_i(1.0F) > 0.9D;
   }

   public boolean func_72896_J() {
      return (double)this.func_72867_j(1.0F) > 0.2D;
   }

   public boolean func_175727_C(BlockPos p_175727_1_) {
      if(!this.func_72896_J()) {
         return false;
      } else if(!this.func_175678_i(p_175727_1_)) {
         return false;
      } else if(this.func_175725_q(p_175727_1_).func_177956_o() > p_175727_1_.func_177956_o()) {
         return false;
      } else {
         BiomeGenBase biomegenbase = this.func_180494_b(p_175727_1_);
         return biomegenbase.func_76746_c()?false:(this.func_175708_f(p_175727_1_, false)?false:biomegenbase.func_76738_d());
      }
   }

   public boolean func_180502_D(BlockPos p_180502_1_) {
      BiomeGenBase biomegenbase = this.func_180494_b(p_180502_1_);
      return biomegenbase.func_76736_e();
   }

   public MapStorage func_175693_T() {
      return this.field_72988_C;
   }

   public void func_72823_a(String p_72823_1_, WorldSavedData p_72823_2_) {
      this.field_72988_C.func_75745_a(p_72823_1_, p_72823_2_);
   }

   public WorldSavedData func_72943_a(Class<? extends WorldSavedData> p_72943_1_, String p_72943_2_) {
      return this.field_72988_C.func_75742_a(p_72943_1_, p_72943_2_);
   }

   public int func_72841_b(String p_72841_1_) {
      return this.field_72988_C.func_75743_a(p_72841_1_);
   }

   public void func_175669_a(int p_175669_1_, BlockPos p_175669_2_, int p_175669_3_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         ((IWorldAccess)this.field_73021_x.get(i)).func_180440_a(p_175669_1_, p_175669_2_, p_175669_3_);
      }

   }

   public void func_175718_b(int p_175718_1_, BlockPos p_175718_2_, int p_175718_3_) {
      this.func_180498_a((EntityPlayer)null, p_175718_1_, p_175718_2_, p_175718_3_);
   }

   public void func_180498_a(EntityPlayer p_180498_1_, int p_180498_2_, BlockPos p_180498_3_, int p_180498_4_) {
      try {
         for(int i = 0; i < this.field_73021_x.size(); ++i) {
            ((IWorldAccess)this.field_73021_x.get(i)).func_180439_a(p_180498_1_, p_180498_2_, p_180498_3_, p_180498_4_);
         }

      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Playing level event");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Level event being played");
         crashreportcategory.func_71507_a("Block coordinates", CrashReportCategory.func_180522_a(p_180498_3_));
         crashreportcategory.func_71507_a("Event source", p_180498_1_);
         crashreportcategory.func_71507_a("Event type", Integer.valueOf(p_180498_2_));
         crashreportcategory.func_71507_a("Event data", Integer.valueOf(p_180498_4_));
         throw new ReportedException(crashreport);
      }
   }

   public int func_72800_K() {
      return 256;
   }

   public int func_72940_L() {
      return this.field_73011_w.func_177495_o()?128:256;
   }

   public Random func_72843_D(int p_72843_1_, int p_72843_2_, int p_72843_3_) {
      long i = (long)p_72843_1_ * 341873128712L + (long)p_72843_2_ * 132897987541L + this.func_72912_H().func_76063_b() + (long)p_72843_3_;
      this.field_73012_v.setSeed(i);
      return this.field_73012_v;
   }

   public BlockPos func_180499_a(String p_180499_1_, BlockPos p_180499_2_) {
      return this.func_72863_F().func_180513_a(this, p_180499_1_, p_180499_2_);
   }

   public boolean func_72806_N() {
      return false;
   }

   public double func_72919_O() {
      return this.field_72986_A.func_76067_t() == WorldType.field_77138_c?0.0D:63.0D;
   }

   public CrashReportCategory func_72914_a(CrashReport p_72914_1_) {
      CrashReportCategory crashreportcategory = p_72914_1_.func_85057_a("Affected level", 1);
      crashreportcategory.func_71507_a("Level name", this.field_72986_A == null?"????":this.field_72986_A.func_76065_j());
      crashreportcategory.func_71500_a("All players", new Callable<String>() {
         public String call() {
            return World.this.field_73010_i.size() + " total; " + World.this.field_73010_i.toString();
         }
      });
      crashreportcategory.func_71500_a("Chunk stats", new Callable<String>() {
         public String call() {
            return World.this.field_73020_y.func_73148_d();
         }
      });

      try {
         this.field_72986_A.func_85118_a(crashreportcategory);
      } catch (Throwable throwable) {
         crashreportcategory.func_71499_a("Level Data Unobtainable", throwable);
      }

      return crashreportcategory;
   }

   public void func_175715_c(int p_175715_1_, BlockPos p_175715_2_, int p_175715_3_) {
      for(int i = 0; i < this.field_73021_x.size(); ++i) {
         IWorldAccess iworldaccess = (IWorldAccess)this.field_73021_x.get(i);
         iworldaccess.func_180441_b(p_175715_1_, p_175715_2_, p_175715_3_);
      }

   }

   public Calendar func_83015_S() {
      if(this.func_82737_E() % 600L == 0L) {
         this.field_83016_L.setTimeInMillis(MinecraftServer.func_130071_aq());
      }

      return this.field_83016_L;
   }

   public void func_92088_a(double p_92088_1_, double p_92088_3_, double p_92088_5_, double p_92088_7_, double p_92088_9_, double p_92088_11_, NBTTagCompound p_92088_13_) {
   }

   public Scoreboard func_96441_U() {
      return this.field_96442_D;
   }

   public void func_175666_e(BlockPos p_175666_1_, Block p_175666_2_) {
      for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
         BlockPos blockpos = p_175666_1_.func_177972_a(enumfacing);
         if(this.func_175667_e(blockpos)) {
            IBlockState iblockstate = this.func_180495_p(blockpos);
            if(Blocks.field_150441_bU.func_149907_e(iblockstate.func_177230_c())) {
               iblockstate.func_177230_c().func_176204_a(this, blockpos, iblockstate, p_175666_2_);
            } else if(iblockstate.func_177230_c().func_149721_r()) {
               blockpos = blockpos.func_177972_a(enumfacing);
               iblockstate = this.func_180495_p(blockpos);
               if(Blocks.field_150441_bU.func_149907_e(iblockstate.func_177230_c())) {
                  iblockstate.func_177230_c().func_176204_a(this, blockpos, iblockstate, p_175666_2_);
               }
            }
         }
      }

   }

   public DifficultyInstance func_175649_E(BlockPos p_175649_1_) {
      long i = 0L;
      float f = 0.0F;
      if(this.func_175667_e(p_175649_1_)) {
         f = this.func_130001_d();
         i = this.func_175726_f(p_175649_1_).func_177416_w();
      }

      return new DifficultyInstance(this.func_175659_aa(), this.func_72820_D(), i, f);
   }

   public EnumDifficulty func_175659_aa() {
      return this.func_72912_H().func_176130_y();
   }

   public int func_175657_ab() {
      return this.field_73008_k;
   }

   public void func_175692_b(int p_175692_1_) {
      this.field_73008_k = p_175692_1_;
   }

   public int func_175658_ac() {
      return this.field_73016_r;
   }

   public void func_175702_c(int p_175702_1_) {
      this.field_73016_r = p_175702_1_;
   }

   public boolean func_175686_ad() {
      return this.field_72987_B;
   }

   public VillageCollection func_175714_ae() {
      return this.field_72982_D;
   }

   public WorldBorder func_175723_af() {
      return this.field_175728_M;
   }

   public boolean func_72916_c(int p_72916_1_, int p_72916_2_) {
      BlockPos blockpos = this.func_175694_M();
      int i = p_72916_1_ * 16 + 8 - blockpos.func_177958_n();
      int j = p_72916_2_ * 16 + 8 - blockpos.func_177952_p();
      int k = 128;
      return i >= -k && i <= k && j >= -k && j <= k;
   }
}
