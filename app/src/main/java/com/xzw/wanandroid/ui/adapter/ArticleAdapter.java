package com.xzw.wanandroid.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzw.wanandroid.R;
import com.xzw.wanandroid.bean.Article;

import javax.inject.Inject;

/**
 * Description : 首页 Item 适配器
 * Author : XZW
 * Date : 2018/9/7
 */
public class ArticleAdapter extends BaseQuickAdapter<Article.DatasBean, BaseViewHolder> {

    private boolean isMyCollection = false;
    private boolean isChapterNameVisible = true;

    @Inject
    public ArticleAdapter() {
        super(R.layout.item_article, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article.DatasBean item) {
        helper.setText(R.id.tvAuthor, item.getAuthor());
        helper.setText(R.id.tvNiceDate, item.getNiceDate());
        helper.setText(R.id.tvTitle, item.getTitle());
        helper.setText(R.id.tvChapterName, item.getChapterName());

        if (isMyCollection) item.setCollect(true);
        helper.setImageResource(R.id.ivCollect, item.isCollect() ? R.drawable.ic_action_like : R.drawable.ic_action_no_like);

        helper.addOnClickListener(R.id.tvChapterName);
        helper.addOnClickListener(R.id.ivCollect);
        helper.setVisible(R.id.tvChapterName, isChapterNameVisible);
    }

    public void setMyCollection(boolean myCollection) {
        isMyCollection = myCollection;
    }

    public void setChapterNameVisible(boolean chapterNameVisible) {
        isChapterNameVisible = chapterNameVisible;
    }
}
