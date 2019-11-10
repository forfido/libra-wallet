<template>
    <v-card
            elevation="24"
            max-width="444"
            class="mx-auto"
    >
        <v-system-bar lights-out></v-system-bar>
        <v-carousel
                :continuous="false"
                :cycle="cycle"
                :show-arrows="false"
                hide-delimiter-background
                delimiter-icon="mdi-minus"
                height="300"
        >
            <v-carousel-item
                    v-for="(slide, i) in slides"
                    :key="i"
            >
                <v-sheet
                        :color="colors[i]"
                        height="100%"
                        tile
                >
                    <v-row
                            class="fill-height"
                            align="center"
                            justify="center"
                    >
                        <div class="display-3">{{ slide }} Slide</div>
                    </v-row>
                </v-sheet>
            </v-carousel-item>
        </v-carousel>
        <v-list two-line>
            <v-list-item>
                <v-list-item-avatar>
                    <v-img src=""></v-img>
                </v-list-item-avatar>
                <v-list-item-content>
                    <v-list-item-title>계정명</v-list-item-title>
                </v-list-item-content>
                <v-list-item-action>
                    <v-btn text icon color="pink">
                        <v-icon>mdi-heart</v-icon>
                    </v-btn>
                    <v-btn text icon color="pink">
                        <v-icon>mdi-heart</v-icon>
                    </v-btn>
                </v-list-item-action>
            </v-list-item>
        </v-list>
    </v-card>
</template>

<script>
    export default {
        data () {
            return {
                colors: [
                    'green',
                    'secondary',
                    'yellow darken-4',
                    'red lighten-2',
                    'orange darken-1',
                ],
                cycle: false,
                slides: [
                    'First',
                    'Second',
                    'Third',
                    'Fourth',
                    'Fifth',
                ],
            }
        },
    }
</script>