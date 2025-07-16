import {createRouter, createWebHistory} from 'vue-router'
import WeekPage from '@/views/WeekPage.vue'
import MyPage from '@/views/MyPage.vue'
import MonthlyWebtoon from "@/components/webtoon/MonthlyWebtoon.vue";
import DailyWebtoon from "@/components/webtoon/DailyWebtoon.vue";
import WebtoonDetail from "@/components/webtoon/WebtoonDetail.vue";
import WebtoonEpisode from "@/components/webtoon/WebtoonEpisode.vue";
import LoginPage from "@/views/LoginPage.vue";
import LikeWebtoons from "@/components/mypage/LikeWebtoons.vue";
import RecentlyViewedWebtoons from "@/components/mypage/RecentlyViewedWebtoons.vue";

const routes = [
    {
        path: '/webtoon',
        name: 'week',
        component: WeekPage,
        children: [
            {
                path: 'all',
                name: 'WebtoonAll',
                component: MonthlyWebtoon,
            },
            {
                path: ':daysOfWeekValue',
                name: 'WebtoonDay',
                component: DailyWebtoon,
                props: true,
            },
            {
                path: 'detail/:webtoonId',
                name: 'WebtoonDetail',
                component: WebtoonDetail,
                props: (route: { params: { webtoonId: any; }; query: { tab: any; }; }) => ({
                    webtoonId: route.params.webtoonId,
                    tab: route.query.tab
                }),
            },
            {
                path: ':webtoonId/episode/:episodeId',
                name: 'WebtoonEpisode',
                component: WebtoonEpisode,
                props: (route: { params: { webtoonId: any; episodeId: any; }; query: { tab: any; }; }) => ({
                    webtoonId: route.params.webtoonId,
                    episodeId: route.params.episodeId,
                    tab: route.query.tab
                }),
            }
        ]
    },
    {
        path: '/mypage',
        name: 'mypage',
        component: MyPage,
        children: [
            {
                path: 'likes',
                name: 'LikedWebtoons',
                component: LikeWebtoons,
            },
            {
                path: 'view-history',
                name: 'ViewHistory',
                component: RecentlyViewedWebtoons,
            },
        ]
    },
    {
        path: '/login',
        name: 'loginpage',
        component: LoginPage,
        meta: { layout: 'none' }
    },
    {
        path: '/',
        redirect: '/webtoon/all',
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

export default router
