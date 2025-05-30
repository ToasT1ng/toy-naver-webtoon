import apiClient from './index'
import {AxiosResponse} from "axios";
import {SortDirection} from "@/types/common";

interface IPagingWebtoonEpisodesRequest {
    webtoonId: number
    pageNo: number
    pageSize: number
    sortDirection: SortDirection
}

interface IEpisode {
    episodeId: number;
    title: string;
    thumbnail: string;
    rating: string;
    uploadDate: string;
}

interface IPagingWebtoonEpisodesResponse {
    webtoonId: number;
    pageNo: number;
    pageSize: number;
    totalCount: number;
    totalPage: number;
    isLastPage: boolean;
    content: IEpisode[];
}

export const getPagingWebtoonEpisodes = async (
    req: IPagingWebtoonEpisodesRequest
): Promise<IPagingWebtoonEpisodesResponse> => {
    const response = await apiClient.get<IPagingWebtoonEpisodesResponse>(`/webtoons/${req.webtoonId}/episodes`, {
        params: {
            pageNo: req.pageNo,
            pageSize: req.pageSize,
            sortDirection: req.sortDirection,
        },
    })
    return response.data
}

interface IWebtoonEpisodeNavigationRequest {
    webtoonId: number
    episodeId: number
}

interface IWebtoonEpisodeNavigationResponse {
    previousEpisodeId: number
    currentEpisodeId: number
    nextEpisodeId: number
}

export const getWebtoonEpisodeNavigation = async (
    req: IWebtoonEpisodeNavigationRequest
): Promise<IWebtoonEpisodeNavigationResponse> => {
    const response = await apiClient.get<IWebtoonEpisodeNavigationResponse>(`/webtoons/${req.webtoonId}/episodes/${req.episodeId}/navigation`)
    return response.data
}