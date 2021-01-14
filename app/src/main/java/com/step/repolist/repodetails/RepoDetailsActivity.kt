package com.step.repolist.repodetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.step.repolist.R
import com.step.repolist.models.Repo

class RepoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_detail)

        val name = intent.getStringExtra(KEY_NAME)
        val owner = intent.getStringExtra(KEY_OWNER_NAME)
        val nameText: TextView = findViewById(R.id.repoName)
        val ownerText: TextView = findViewById(R.id.ownerName)

        nameText.text = name
        ownerText.text = "@$owner"
    }

    companion object {
        const val KEY_NAME = "key_name"
        const val KEY_OWNER_NAME = "key_owner_name"

        fun startActivity(context: Context, repo: Repo) {
            val intent = Intent(context, RepoDetailsActivity::class.java)
            intent.putExtra(KEY_NAME, repo.name)
            intent.putExtra(KEY_OWNER_NAME, repo.owner.login)
            context.startActivity(intent)
        }
    }
}