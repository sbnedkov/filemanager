" set runtimepath=./.vim,~/.vim,$VIM/vimfiles,$VIMRUNTIME,$VIM/vimfiles/after,./.vim/after,~/.vim/after
" set packpath=./.vim,~/.vim,$VIM/vimfiles,$VIMRUNTIME,$VIM/vimfiles/after,./.vim/after,~/.vim/after
set packpath=./.vim,~/.vim
" let g:jsx_ext_required = 0
autocmd BufWritePost *.java !./mvnw compile
