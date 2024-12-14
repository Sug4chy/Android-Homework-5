package ru.sug4chy.smarthouselightbulb.data.model

enum class LightbulbState {
    ON,
    OFF;

    companion object {

        fun fromBoolean(boolean: Boolean): LightbulbState =
            when (boolean) {
                true -> ON
                false -> OFF
            }
    }
}