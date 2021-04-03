package com.step.repolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Json
import com.step.repolist.api.SearchResult
import com.step.repolist.api.createGithubApiService
import com.step.repolist.repodetails.RepoDetailsActivity
import com.step.repolist.reposlist.ReposAdapter
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ReposAdapter { RepoDetailsActivity.startActivity(this, it) }

        val list: RecyclerView = this.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter

        val service = createGithubApiService()
        service.searchRepositories("android").enqueue(object : Callback<SearchResult> {
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                val repos = response.body()?.items.orEmpty()
                adapter.submitList(repos)
            }

        })
    }
}