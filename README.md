# thinking_in_java
java编程思想

# 删除已经提交到本地的大文件
git filter-branch --index-filter 'git rm --cached --ignore-unmatch <file_name>'
rm -rf .git/refs/original/
git reflog expire --expire=now --all
git fsck --full --unreachable
git repack -A -d
git gc --aggressive --prune=now
git push --force [remote] master