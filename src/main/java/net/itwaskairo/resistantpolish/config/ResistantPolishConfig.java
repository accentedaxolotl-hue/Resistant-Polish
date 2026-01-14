package net.itwaskairo.resistantpolish.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ResistantPolishConfig {

        public static final ForgeConfigSpec COMMON_SPEC;
        public static final Common COMMON;

        public static final ForgeConfigSpec SERVER_SPEC;
        public static final Server SERVER;

        static {
            Pair<Common, ForgeConfigSpec> commonPair =
                    new ForgeConfigSpec.Builder().configure(Common::new);
            COMMON = commonPair.getLeft();
            COMMON_SPEC = commonPair.getRight();

            Pair<Server, ForgeConfigSpec> serverPair =
                    new ForgeConfigSpec.Builder().configure(Server::new);
            SERVER = serverPair.getLeft();
            SERVER_SPEC = serverPair.getRight();
        }

        public static class Common {
            public final ForgeConfigSpec.IntValue slightPolishValue;
            public final ForgeConfigSpec.IntValue normalPolishValue;
            public final ForgeConfigSpec.IntValue veryPolishValue;

            Common(ForgeConfigSpec.Builder builder) {
                builder.push("polish_values");

                slightPolishValue = builder
                        .comment("Resistance values for different polishes")
                        .defineInRange("slight_value", 5, 0, 100);

                normalPolishValue = builder
                        .defineInRange("normal_value", 8, 0, 100);

                veryPolishValue = builder
                        .defineInRange("very_value", 12, 0, 100);

                builder.pop();
            }
        }

        public static class Server {
            public final ForgeConfigSpec.IntValue resistantPolishDecayValue;

            Server(ForgeConfigSpec.Builder builder) {
                resistantPolishDecayValue = builder
                        .comment("Decay value for resistant polish")
                        .defineInRange("decay_value", -5, -100, 100);
            }
        }
}