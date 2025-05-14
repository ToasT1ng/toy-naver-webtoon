import router from "@/router";

export const navigateToWebtoonDetail = (webtoonId: number, tab: string) => {
    router.push({
        path: `/webtoon/detail/${webtoonId}`,
        query: {tab}
    }).then(r => {
        // console.log(r)
    })
 }

export const navigateToWebtoonEpisode = (episodeId: number, tab: string)=> {
    router.push({
        path: `/webtoon/episode/${episodeId}`,
        query: {tab}
    }).then(r => {
        // console.log(r)
    })
}