package com.liulije.readerdemo.book.bean;

import com.allen.library.base.BaseResponse;

import java.io.Serializable;

/**
 * @类名称: CLASS
 * @类描述:书籍章节内容
 * @创建人：LiuL1Jie
 * @创建时间：2017/8/16 10:54
 * @备注：
 */
public class ChapterRead extends BaseResponse implements Serializable {
    private ChapterRead chapter;

    /**
     * title : .
     * body : 活动时间：2013年11月11日——2013年12月11日 原著可选范围：17K签约作品 1，什么是同人？ 定义版：在原创作品中的一些被塑造的虚拟人物在二次创作下，扮演不同的故事。 白话版：fans看原著后，有自己的想法，借用原著的人物和背景，再次进行创作，改下人物命运，发泄个人的各种不情愿。 2，如何参与活动？ ① 重新发新书（请注意，参与活动需要重新发新书，最迟首章发稿期限为2013年12月11日）； ② 选择“同人频道”或者女生网“耽美同人频道”下的“女生同人”类别，在简介中写上“【签约作品《XXX》同人小说】” 3，频道类别错了怎么办？简介忘记写【签约作品《XXX》同人小说】怎么办？ 改频道类别：作者后台——作品管理——作品信息管理——修改频道/类别 改简介：作者后台——作品管理——作品信息管理——重新填写简介 活动奖励： 被同人的原著奖励： 一、参与活动的同人作品数：截至2013年12月11日，参与活动的同人作品最多的原著奖励千元章一枚+kindle一部；（注：作品数需不小于5） 二、参与活动的同人作品存活数：截至2013年12月11日，参与活动的同人作品保持连更最多的原著奖励千元章一枚；（注：作品数不小于5，连更天数不小于1） 三、被同人的原著参与有奖：拥有5本参与活动原著的同人作品，则奖励原著作者10枚标准章，且每增加5本参与活动的同人作品，原著将获得10枚标准章。 四、签约有礼：每签约一本同人作品，奖励原著100枚标准章； 参与活动的同人作品奖励： 一、发新书有奖：参与活动的同人作品，正文字数过3万字后，奖励10枚标准章； 二、米币双倍：参与活动的同人作品，开书一个月内米币双倍发放； 三、发新书就有推荐：新增同人频道，同人作品字数达2万字后，即可轮流上推； 四、签约有礼：每签约一本同人作品，奖励同人作品100枚标准章+价值30元包月激活码一个； 五、连更有奖：参与活动的同人作品，连续更新30天，奖励价值30元包月激活码一个；（每部作品仅限一次）
     */

    private String title;

    public ChapterRead getChapter() {
        return chapter;
    }

    public void setChapter(ChapterRead chapter) {
        this.chapter = chapter;
    }

    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
