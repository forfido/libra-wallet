
import "@fortawesome/fontawesome-free/css/all.css";
import "@mdi/font/css/materialdesignicons.css";
import "material-design-icons-iconfont/dist/material-design-icons.css";

import Vue from "vue";
import Vuetify, {
  VSnackbar,
  VIcon,
  VDialog,
  VCard,
  VCardTitle,
  VCardSubtitle,
  VCardText,
  VCardActions,
  VSpacer,
  VBtn,
  VToolbar,
  VToolbarTitle,
  VAlert
} from "vuetify/lib";
import "vuetify/src/stylus/app.styl";

Vue.use(Vuetify, {
  components: {
    VSnackbar,
    VIcon,
    VDialog,
    VCard,
    VCardTitle,
    VCardSubtitle,
    VCardText,
    VCardActions,
    VSpacer,
    VBtn,
    VToolbar,
    VToolbarTitle,
    VAlert
  },
  iconfont: "md" || "fa" || "mdi"
});
