release: --pythonpath movieflix python manage.py makemigrations --no-input
release: --pythonpath movieflix python manage.py migrate --no-input

web: gunicorn --pythonpath movieflix movieflix.wsgi --log-file -
