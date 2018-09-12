package com.xzw.wanandroid.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xzw.wanandroid.R;
import com.xzw.wanandroid.bean.KnowledgeHierarchy;

import javax.inject.Inject;

/**
 * Description :
 * Author : XZW
 * Date : 2018/9/7
 */
public class KnowledgeHierarchyAdapter extends BaseQuickAdapter<KnowledgeHierarchy, BaseViewHolder> {

    @Inject
    public KnowledgeHierarchyAdapter() {
        super(R.layout.item_knowledge_hierarchy, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeHierarchy item) {
        helper.setText(R.id.typeItemFirst, item.getName());

        StringBuilder sb = new StringBuilder();
        for (KnowledgeHierarchy children : item.getChildren()) {
            sb.append(children.getName()).append("    ");
        }
        helper.setText(R.id.typeItemSecond, sb.toString());
    }
}
