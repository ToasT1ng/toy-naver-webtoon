import {createApp} from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import {loadFonts} from './plugins/webfontloader'
import router from './router'
import './assets/global.css'
import {VueQueryPlugin, QueryClient} from '@tanstack/vue-query'
import {createPinia} from 'pinia'

const queryClient = new QueryClient()
const pinia = createPinia()

loadFonts()

createApp(App)
    .use(vuetify)
    .use(router)
    .use(VueQueryPlugin, {queryClient})
    .use(pinia)
    .mount('#app')
