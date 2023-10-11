package com.wanandroid.module_article.ui.project

import androidx.lifecycle.MutableLiveData
import com.wanandroid.module_article.entity.ProjectTreeEntity
import com.wanandroid.module_article.net.ArticleRepository
import com.wanandroid.module_base.base.BaseViewModel

/**
 * @author wzh
 * @date 2023/10/9
 */
class ProjectViewModel(private val repository: ArticleRepository) : BaseViewModel() {

    val projectTreeEntity by lazy { MutableLiveData<List<ProjectTreeEntity>>() }
    fun requestProjectTree() = launchRequest {
        projectTreeEntity.value = repository.getProjectTree()
    }
}