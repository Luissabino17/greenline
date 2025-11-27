// Mobile menu functionality
document.addEventListener('DOMContentLoaded', function() {
    const mobileMenu = document.querySelector('.mobile-menu');
    const navLinks = document.querySelector('.nav-links');
    
    if (mobileMenu) {
        mobileMenu.addEventListener('click', function() {
            this.classList.toggle('active');
            navLinks.classList.toggle('active');
        });
    }
    
    // Close mobile menu when clicking on a link
    document.querySelectorAll('.nav-links a').forEach(link => {
        link.addEventListener('click', function() {
            mobileMenu.classList.remove('active');
            navLinks.classList.remove('active');
        });
    });
    
    // Smooth scrolling for anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();
            
            const targetId = this.getAttribute('href');
            if (targetId === '#') return;
            
            const targetElement = document.querySelector(targetId);
            if (targetElement) {
                const headerHeight = document.querySelector('header').offsetHeight;
                const targetPosition = targetElement.offsetTop - headerHeight;
                
                window.scrollTo({
                    top: targetPosition,
                    behavior: 'smooth'
                });
            }
        });
    });
    
    // Gallery slider functionality
    const gallerySlider = document.querySelector('.gallery-slider');
    const galleryItems = document.querySelectorAll('.gallery-item');
    const prevButton = document.querySelector('.gallery-prev');
    const nextButton = document.querySelector('.gallery-next');
    let currentSlide = 0;
    
    function updateGallery() {
        if (gallerySlider) {
            gallerySlider.style.transform = `translateX(-${currentSlide * 100}%)`;
        }
    }
    
    if (prevButton && nextButton) {
        prevButton.addEventListener('click', function() {
            currentSlide = (currentSlide > 0) ? currentSlide - 1 : galleryItems.length - 1;
            updateGallery();
        });
        
        nextButton.addEventListener('click', function() {
            currentSlide = (currentSlide < galleryItems.length - 1) ? currentSlide + 1 : 0;
            updateGallery();
        });
        
        // Auto slide every 5 seconds
        setInterval(function() {
            currentSlide = (currentSlide < galleryItems.length - 1) ? currentSlide + 1 : 0;
            updateGallery();
        }, 5000);
    }
    
    // Form submission for booking
    const bookingForm = document.getElementById('bookingForm');
    if (bookingForm) {
        bookingForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Simple form validation
            const name = document.getElementById('name').value;
            const service = document.getElementById('service').value;
            const date = document.getElementById('date').value;
            
            if (!name || !service || !date) {
                alert('Please fill in all required fields.');
                return;
            }
            
            // Show success message
            alert('Thank you for your booking! We will contact you shortly to confirm your garden service.');
            
            // Reset form
            this.reset();
            
            // Set minimum date to today
            setMinDate();
        });
    }
    
    // Form submission for reviews
    const reviewForm = document.getElementById('reviewForm');
    if (reviewForm) {
        reviewForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Simple form validation
            const name = document.getElementById('reviewName').value;
            const location = document.getElementById('reviewLocation').value;
            const rating = document.querySelector('input[name="rating"]:checked');
            const reviewText = document.getElementById('reviewText').value;
            
            if (!name || !location || !rating || !reviewText) {
                alert('Please fill in all fields and select a rating.');
                return;
            }
            
            // Show success message
            alert('Thank you for your review! It will be published after moderation.');
            
            // Reset form
            this.reset();
            
            // Reset star rating
            document.querySelectorAll('input[name="rating"]').forEach(radio => {
                radio.checked = false;
            });
        });
    }
    
    // Set minimum date to today for booking form
    function setMinDate() {
        const today = new Date();
        const dd = String(today.getDate()).padStart(2, '0');
        const mm = String(today.getMonth() + 1).padStart(2, '0');
        const yyyy = today.getFullYear();
        const minDate = yyyy + '-' + mm + '-' + dd;
        
        const dateInput = document.getElementById('date');
        if (dateInput) {
            dateInput.setAttribute('min', minDate);
        }
    }
    
    // Initialize min date
    setMinDate();
    
    // Header scroll effect
    window.addEventListener('scroll', function() {
        const header = document.querySelector('header');
        if (window.scrollY > 100) {
            header.style.padding = '10px 0';
            header.style.boxShadow = '0 10px 30px rgba(0,0,0,0.1)';
        } else {
            header.style.padding = '15px 0';
            header.style.boxShadow = '0 10px 30px rgba(0,0,0,0.08)';
        }
    });
    
    // Add subtle animations to elements when they come into view
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };
    
    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, observerOptions);
    
    // Observe elements for animation
    document.querySelectorAll('.step, .service-card, .review-card').forEach(el => {
        el.style.opacity = '0';
        el.style.transform = 'translateY(20px)';
        el.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
        observer.observe(el);
    });
});