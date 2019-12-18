<template>
    <v-card
            elevation="24"
            max-width="444"
            class="mx-auto"
            color="teal accent-4"
    >
        <v-card-title class="headline teal accent-4">
            <v-icon>account_tree</v-icon>
            <b class="font-italic">&nbsp;{{this.$router.currentRoute.name}}</b>
        </v-card-title>
        <v-carousel
                :cycle="cycle"
                :show-arrows="false"
                hide-delimiter-background
                delimiter-icon="mdi-minus"
                height="300"
                v-model="carouselIndex"
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
                    <v-list-item-title>
                        <span>Current Index: {{carouselIndex}}</span>
                    </v-list-item-title>
                </v-list-item-content>
                <v-list-item-action>
                    <v-switch
                            v-model="cycle"
                            label="Select Account"
                            inset
                    ></v-switch>
                </v-list-item-action>
            </v-list-item>
        </v-list>
    </v-card>
</template>

<script>
    export default {
        data: () => ({
            colors: [
                'green',
                'secondary',
                'yellow darken-4',
                'red lighten-2',
                'orange darken-1',
            ],
            cycle: false,
            slides: [
                '1 Account',
                '2 Account',
                '3 Account',
                '4 Account',
                '5 Account',
            ],
            carouselIndex: 0

        }),
    }
</script>