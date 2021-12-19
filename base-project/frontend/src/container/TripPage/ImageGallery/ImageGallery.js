import React from 'react';
import ImageGallery from 'react-image-gallery';
import 'react-image-gallery/styles/css/image-gallery.css';
import ImageGalleryWrapper from './ImageGallery.style';

const images = [
    {
        original: '/images/post/0.jpg',
        thumbnail: '/images/post/0.jpg',
    },
    {
        original: '/images/post/1.jpg',
        thumbnail: '/images/post/1.jpg',
    },
    {
        original: '/images/post/2.jpg',
        thumbnail: '/images/post/2.jpg',
    },
    {
        original: '/images/post/3.jpg',
        thumbnail: '/images/post/3.jpg',
    },
    {
        original: '/images/post/4.jpg',
        thumbnail: '/images/post/4.jpg',
    },
];

const PostImageGallery = (props) => {

    const images = []

    props.gallery.forEach( item => {
        let image = {
            original: item.url,
            thumbnail: item.url
        }
        images.push(image);
    });

    return (
        <ImageGalleryWrapper>
            <ImageGallery
                items={images}
                showPlayButton={false}
                showFullscreenButton={false}
                showIndex={true}
                lazyLoad={true}
                slideDuration={550}
            />
        </ImageGalleryWrapper>
    );
};

export default PostImageGallery;
