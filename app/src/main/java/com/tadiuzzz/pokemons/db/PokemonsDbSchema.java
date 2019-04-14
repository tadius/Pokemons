package com.tadiuzzz.pokemons.db;

public class PokemonsDbSchema {

    //Внутренний класс для описания таблицы
    public static final class PokemonsTable {
        public static final String NAME = "pokemons";

        public static final class Cols {
            public static final String NAMEOFPOKEMON = "nameofpokemon";
            public static final String ID = "idofpokemon";
            public static final String SPRITEFRONT = "spritefront";
            public static final String SPRITEBACK = "spriteback";
        }
    }

    public static final class AbilitiesTable {
        public static final String NAME = "abilities";

        public static final class Cols {
            public static final String ID = "idofpokemon";
            public static final String ABILITYNAME = "abilityname";
        }

    }

    public static final class StatsTable {
        public static final String NAME = "stats";

        public static final class Cols {
            public static final String ID = "idofpokemon";
            public static final String STATNAME = "statname";
            public static final String STATBASE = "statbase";
        }

    }

}
