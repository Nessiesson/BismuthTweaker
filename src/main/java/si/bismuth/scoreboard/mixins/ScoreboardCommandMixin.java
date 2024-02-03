package si.bismuth.scoreboard.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardScore;
import net.minecraft.server.command.ScoreboardCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import si.bismuth.scoreboard.IScoreboardScore;

@Mixin(ScoreboardCommand.class)
public class ScoreboardCommandMixin {
    // TODO in same method try to parse a long should be easier with the mixinextras
    @Redirect(method = "setScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V"))
    private void setScore(ScoreboardScore instance, int score, @Local(ordinal = 1) String owner, @Local ScoreboardObjective scoreboardObjective) {
        ((IScoreboardScore) instance).bismuthServer$setLongScore((long) score, owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 0))
    private void modifyScorePlus(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective) {
        long score1 = ((IScoreboardScore) scoreboardScore).bismuthServer$getLongScore();
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(score1 + score2, owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 1))
    private void modifyScoreMin(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective) {
        long score1 = ((IScoreboardScore) scoreboardScore).bismuthServer$getLongScore();
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(score1 - score2, owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 2))
    private void modifyScoreMult(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective) {
        long score1 = ((IScoreboardScore) scoreboardScore).bismuthServer$getLongScore();
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(score1 * score2, owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 3))
    private void modifyScoreDiv(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective) {
        long score1 = ((IScoreboardScore) scoreboardScore).bismuthServer$getLongScore();
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(score1 / score2, owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 4))
    private void modifyScoreMod(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective) {
        long score1 = ((IScoreboardScore) scoreboardScore).bismuthServer$getLongScore();
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(score1 % score2, owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 5))
    private void modifyScoreEq(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective) {
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(score2, owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 6))
    private void modifyScoreLess(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective) {
        long score1 = ((IScoreboardScore) scoreboardScore).bismuthServer$getLongScore();
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(Math.min(score1, score2), owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 7))
    private void modifyScoreGreater(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective) {
        long score1 = ((IScoreboardScore) scoreboardScore).bismuthServer$getLongScore();
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(Math.max(score1, score2), owner, scoreboardObjective);
    }

    @Redirect(method = "modifyScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/ScoreboardScore;set(I)V", ordinal = 8))
    private void modifyScoreWeirdOp(ScoreboardScore instance, int score, @Local(ordinal = 0) ScoreboardScore scoreboardScore, @Local(ordinal = 1) ScoreboardScore scoreboardScore2, @Local(ordinal = 0) String owner1, @Local(ordinal = 0) ScoreboardObjective scoreboardObjective1, @Local(ordinal = 2) String owner2, @Local(ordinal = 1) ScoreboardObjective scoreboardObjective2) {
        long score1 = ((IScoreboardScore) scoreboardScore).bismuthServer$getLongScore();
        long score2 = ((IScoreboardScore) scoreboardScore2).bismuthServer$getLongScore();
        ((IScoreboardScore) scoreboardScore).bismuthServer$setLongScore(score2, owner1, scoreboardObjective1);
        ((IScoreboardScore) scoreboardScore2).bismuthServer$setLongScore(score1, owner2, scoreboardObjective2);
    }
}
