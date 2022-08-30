package com.minecraftabnormals.biome_vote_losers.data;

import com.minecraftabnormals.biome_vote_losers.BiomeVoteLosers;
import com.minecraftabnormals.biome_vote_losers.register.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class BlockstateGenerator extends BlockStateProvider {
	public BlockstateGenerator(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, BiomeVoteLosers.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.simpleBlock(ModBlocks.MOUND.get());
		this.simpleBlock(ModBlocks.TERMITE_MOUND.get());

		this.leavesTintBlock(ModBlocks.BAOBAB_LEAVES.get());
		this.logBlock(ModBlocks.BAOBAB_TRUNK.get());

		this.logBlock(ModBlocks.PALM_LOG.get());
		this.leavesTintBlock(ModBlocks.PALM_LEAVES.get());
		// todo PALM_LEAVES_HANGING
		this.logBlock(ModBlocks.STRIPPED_PALM_LOG.get());
		this.simpleBlock(ModBlocks.PALM_WOOD.get(), texture(name(ModBlocks.PALM_LOG.get())));
		this.simpleBlock(ModBlocks.STRIPPED_PALM_WOOD.get(), texture(name(ModBlocks.STRIPPED_PALM_LOG.get())));
		// todo fix logs and wood looking the same
		this.simpleBlock(ModBlocks.PALM_PLANKS.get());
		this.stairs(ModBlocks.PALM_STAIRS.get(), ModBlocks.PALM_PLANKS.get());
		this.slab(ModBlocks.PALM_SLAB.get(), ModBlocks.PALM_PLANKS.get());
		this.crossBlock(ModBlocks.PALM_SAPLING.get());

		this.doorBlock(ModBlocks.PALM_DOOR.get(), texture(name(ModBlocks.PALM_DOOR.get()) + "_bottom"), texture(name(ModBlocks.PALM_DOOR.get()) + "_top"));
		this.trapdoorBlock(ModBlocks.PALM_TRAPDOOR.get(), texture(name(ModBlocks.PALM_TRAPDOOR.get())), true);
		// todo make door and trapdoor transparent

		// todo PALM_SIGN
		// todo PALM_WALL_SIGN

		this.fenceBlock(ModBlocks.PALM_FENCE.get(), texture(name(ModBlocks.PALM_PLANKS.get())));
		this.fenceGateBlock(ModBlocks.PALM_FENCE_GATE.get(), texture(name(ModBlocks.PALM_PLANKS.get())));
		this.buttonBlock(ModBlocks.PALM_BUTTON.get(), texture(name(ModBlocks.PALM_PLANKS.get())));
		this.pressurePlateBlock(ModBlocks.PALM_PRESSURE_PLATE.get(), texture(name(ModBlocks.PALM_PLANKS.get())));

	}

	public ModelFile cubeAll(Block block, ResourceLocation resourceLocation) {
		return models().cubeAll(name(block), resourceLocation);
	}

	public void simpleBlock(Block block, ResourceLocation resourceLocation) {
		simpleBlock(block, cubeAll(block, resourceLocation));
	}


	public void leavesTintBlock(Block block) {
		ModelFile tint = models().singleTexture(name(block), mcLoc("minecraft:block/leaves"), "all", texture(name(block))).renderType("minecraft:cutout_mipped");
		leavesBlock(block, tint);
	}

	private void leavesBlock(Block block, ModelFile model) {
		getVariantBuilder(block).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(model)
						.build());
	}

	public void translucentBlock(Block block) {
		simpleBlock(block, translucentCubeAll(block));
	}

	private ModelFile translucentCubeAll(Block block) {
		return models().cubeAll(name(block), blockTexture(block)).renderType("minecraft:translucent");
	}

	public void stairs(StairBlock block, Block fullBlock) {
		stairsBlock(block, texture(name(fullBlock)));
	}

	public void slab(SlabBlock block, Block fullBlock) {
		slabBlock(block, texture(name(fullBlock)), texture(name(fullBlock)));
	}

	public void crossBlock(Block block) {

		crossBlock(block, models().cross(name(block), texture(name(block))).renderType("minecraft:cutout"));
	}

	public void crossTintBlock(Block block) {
		ModelFile tint = models().singleTexture(name(block), mcLoc("block/tinted_cross"), "cross", texture(name(block))).renderType("minecraft:cutout");


		crossBlock(block, tint);
	}

	private void crossBlock(Block block, ModelFile model) {
		getVariantBuilder(block).forAllStates(state ->
				ConfiguredModel.builder()
						.modelFile(model)
						.build());
	}


	private ResourceLocation key(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block);
	}

	protected ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	protected String name(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	@Nonnull
	@Override
	public String getName() {
		return "BiomeVoteLosers blockstates and block models";
	}
}