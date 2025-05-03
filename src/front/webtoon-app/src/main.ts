import {createApp} from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import {loadFonts} from './plugins/webfontloader'
import router from './router'
import './assets/main.css'
import {VueQueryPlugin, QueryClient} from '@tanstack/vue-query'

const queryClient = new QueryClient()

loadFonts()

createApp(App)
    .use(vuetify)
    .use(router)
    .use(VueQueryPlugin, {queryClient})
    .mount('#app')
